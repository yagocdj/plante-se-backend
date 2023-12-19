package com.plantese.api.controller;

import com.plantese.api.models.DadosPedidoInserirDTO;
import com.plantese.api.models.Pedido;
import com.plantese.api.models.PedidoListagemDTO;
import com.plantese.api.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoListagemDTO> listar() {
        return this.pedidoService.listar();
    }

    @PostMapping
    public Pedido inserir(@RequestBody @Valid DadosPedidoInserirDTO pedido) {
        var pedidoASerInserido = new Pedido(
                pedido.cliente(),
                pedido.produtos(),
                pedido.valorTotal()
        );
        return this.pedidoService.inserirOuAtualizar(pedidoASerInserido);
    }

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable("id") Long id) {
        this.pedidoService.apagar(id);
    }
}
