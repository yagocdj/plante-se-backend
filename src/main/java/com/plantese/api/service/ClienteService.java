package com.plantese.api.service;

import com.plantese.api.models.Cliente;
import com.plantese.api.models.ClienteListagemDTO;
import com.plantese.api.repository.IClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public boolean autenticar(String email, String senha) {
        Optional<Cliente> clienteRegistrado = this.clienteRepository.findByEmail(email);
        if (clienteRegistrado.isEmpty()) {
            throw new RuntimeException("Cliente de email " + email + " não registrado.");
        }
        return clienteRegistrado.get().getSenha().equals(senha);
    }

    @Transactional
    public Cliente inserirOuAtualizar(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public ClienteListagemDTO getClientePorEmail(String email) {
        var clientePesquisado = this.clienteRepository.findByEmail(email);
        return clientePesquisado.map(ClienteListagemDTO::new).orElse(null);
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
