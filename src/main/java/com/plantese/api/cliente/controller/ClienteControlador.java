package com.plantese.api.cliente.controller;

import com.plantese.api.cliente.model.Cliente;
import com.plantese.api.cliente.model.ClienteListagemDTO;
import com.plantese.api.cliente.model.DadosClienteInserirDTO;
import com.plantese.api.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteListagemDTO> listar(@RequestParam(value = "email", required = false) String email) {
        if (email != null && !email.isBlank()) {
            var clientePesquisado = this.clienteService.getClientePorEmail(email);
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
            cliente.telefone()
        );
        return this.clienteService.inserirOuAtualizar(clienteASerInserido);
    }

    @PutMapping("/{id")
    public Cliente atualizar(@RequestBody Cliente cliente) {
        return this.clienteService.inserirOuAtualizar(cliente);
    }
}
