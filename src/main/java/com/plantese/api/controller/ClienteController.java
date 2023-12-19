package com.plantese.api.controller;

import com.plantese.api.models.Cliente;
import com.plantese.api.models.ClienteListagemDTO;
import com.plantese.api.models.DadosClienteInserirDTO;
import com.plantese.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteListagemDTO> listar(@RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "cpf", required = false) String cpf) {

        boolean temEmail = email != null && !email.isBlank();
        boolean temCpf = (cpf != null && !cpf.isBlank()) && cpf.length() == 11;

        if (temEmail) {
            var clientePesquisado = this.clienteService.getClientePorEmail(email);
            if (clientePesquisado != null) {
                return Collections.singletonList(clientePesquisado);
            }
            return null;
        }
        if (temCpf) {
            var clientePesquisado = this.clienteService.getClienteByCpf(cpf);
            if (clientePesquisado != null) {
                return Collections.singletonList(clientePesquisado);
            }
            return null;
        }
        return this.clienteService.listar();
    }

    @GetMapping("/{id}")
    public Cliente getClientePorId(@PathVariable("id") Long idCliente) {
        return this.clienteService.getClienteById(idCliente);
    }

    @PostMapping
    public Cliente inserir(@RequestBody @Valid DadosClienteInserirDTO cliente) {
        var clienteASerInserido = new Cliente(
                cliente.nome(),
                cliente.cpf(),
                cliente.email(),
                cliente.endereco(),
                cliente.telefone(),
                cliente.senha()
        );
        return this.clienteService.inserirOuAtualizar(clienteASerInserido);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@RequestBody Cliente cliente) {
        return this.clienteService.inserirOuAtualizar(cliente);
    }

    @DeleteMapping("/{id}")
    public void apagar(@PathVariable("id") Long id) {
        this.clienteService.apagar(id);
    }
}
