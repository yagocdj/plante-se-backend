package com.plantese.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plantese.api.cliente.model.Cliente;

import java.util.List;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Método para acessar o banco de dados e ler todos os clientes.
     * @return todos os clientes do banco.
     */
    public List<Cliente> findAll();

    /**
     * Método para localizar um cliente a partir do seu endereço de email.
     * @param email o email do cliente a ser localizado.
     * @return o cliente cujo email é igual ao passado como argumento deste método.
     */
    public Cliente findClienteByEmail(String email);
}
