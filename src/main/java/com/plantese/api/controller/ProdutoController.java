package com.plantese.api.controller;

import com.plantese.api.models.DadosProdutoInserirDTO;
import com.plantese.api.models.Produto;
import com.plantese.api.models.ProdutoListagemDTO;
import com.plantese.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoListagemDTO> listar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "categoria", required = false) String categoria) {

        boolean temNome = nome != null && !nome.isBlank();
        boolean temCategoria = categoria != null && !categoria.isBlank();

        if (temNome) {
            var produtoPesquisado = this.produtoService.getProdutoByNome(nome);
            if (produtoPesquisado != null) {
                return Collections.singletonList(produtoPesquisado);
            }
            return null;
        }
        if (temCategoria) {
            return this.produtoService.getProdutosByCategoria(categoria);
        }

        return this.produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto getProdutoPorId(@PathVariable("id") Long idProduto) {
        return this.produtoService.getProdutoById(idProduto);
    }

    @PostMapping
    public Produto inserir(@RequestBody @Valid DadosProdutoInserirDTO produto) {
        var produtoASerInserido = new Produto(
                produto.nome(),
                produto.preco(),
                produto.categoria(),
                produto.urlDaImagem()
        );
        return this.produtoService.inserirOuAtualizar(produtoASerInserido);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody Produto produto) {
        return this.produtoService.inserirOuAtualizar(produto);
    }

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable("id") Long id) {
        this.produtoService.apagar(id);
    }
}
