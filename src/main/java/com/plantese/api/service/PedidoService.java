package com.plantese.api.service;

import com.plantese.api.models.Cliente;
import com.plantese.api.models.Pedido;
import com.plantese.api.models.PedidoListagemDTO;
import com.plantese.api.models.Produto;
import com.plantese.api.repository.IClienteRepository;
import com.plantese.api.repository.IPedidoRepository;
import com.plantese.api.repository.IProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final IPedidoRepository pedidoRepository;
    private final IClienteRepository clienteRepository;
    private final IProdutoRepository produtoRepository;

    public PedidoService(
            IPedidoRepository pedidoRepository,
            IClienteRepository clienteRepository,
            IProdutoRepository produtoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<PedidoListagemDTO> listar() {
        return this.pedidoRepository.findAll().stream().map(PedidoListagemDTO::new).toList();
    }

    @Transactional
    public Pedido inserirOuAtualizar(Pedido pedido) {
        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            Cliente clientePersistente = this.clienteRepository.findById(pedido.getCliente().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));
            pedido.setCliente(clientePersistente);
        }
        if (!pedido.getProdutos().isEmpty()) {
            List<Produto> produtosPersistidos = this.produtoRepository.findAll();
            pedido.setProdutos(produtosPersistidos);
        }
        return this.pedidoRepository.save(pedido);
    }

    @Transactional
    public void apagar(Long id) {
        this.pedidoRepository.deleteById(id);
    }
}
