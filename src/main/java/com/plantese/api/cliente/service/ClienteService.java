package com.plantese.api.cliente.service;

import com.plantese.api.cliente.model.Cliente;
import com.plantese.api.cliente.model.ClienteListagemDTO;
import com.plantese.api.cliente.repository.IClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    public List<ClienteListagemDTO> listar() {
        return this.clienteRepository.findAll().stream().map(ClienteListagemDTO::new).toList();
    }

    public Cliente getClienteById(Long id) {
        return this.clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cliente inserirOuAtualizar(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public ClienteListagemDTO getClientePorEmail(String email) {
        var clientePesquisado = this.clienteRepository.findClienteByEmail(email);
        if (clientePesquisado != null) {
            return new ClienteListagemDTO(clientePesquisado);
        }
    }
}
