package com.plantese.api.service;

import com.plantese.api.models.Pedido;
import com.plantese.api.models.PedidoListagemDTO;
import com.plantese.api.repository.IPedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final IPedidoRepository pedidoRepository;

    public PedidoService(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoListagemDTO> listar() {
        return this.pedidoRepository.findAll().stream().map(PedidoListagemDTO::new).toList();
    }

    @Transactional
    public Pedido inserirOuAtualizar(Pedido pedido) {
        return this.pedidoRepository.save(pedido);
    }

    @Transactional
    public void apagar(Long id) {
        this.pedidoRepository.deleteById(id);
    }
}
