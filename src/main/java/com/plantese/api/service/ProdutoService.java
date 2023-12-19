package com.plantese.api.service;

import com.plantese.api.models.Produto;
import com.plantese.api.models.ProdutoListagemDTO;
import com.plantese.api.repository.IProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final IProdutoRepository produtoRepository;

    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoListagemDTO> listar() {
        return this.produtoRepository.findAll().stream().map(ProdutoListagemDTO::new).toList();
    }

    public Produto getProdutoById(Long id) {
        return this.produtoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Produto inserirOuAtualizar(Produto produto) {
        return this.produtoRepository.save(produto);
    }

    @Transactional
    public void apagar(Long id) {
        this.produtoRepository.deleteById(id);
    }
}
