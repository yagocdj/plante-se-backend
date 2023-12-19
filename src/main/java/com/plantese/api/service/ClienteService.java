package com.plantese.api.service;

import com.plantese.api.models.Cliente;
import com.plantese.api.models.ClienteListagemDTO;
import com.plantese.api.repository.IClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

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
        return null;
    }

    public ClienteListagemDTO getClienteByCpf(String cpf) {
        var clientePesquisado = this.clienteRepository.findClienteByCpf(cpf);
        if (clientePesquisado != null) {
            return new ClienteListagemDTO(clientePesquisado);
        }
        return null;
    }

    @Transactional
    public void apagar(Long id) {
        this.clienteRepository.deleteById(id);
    }
}
