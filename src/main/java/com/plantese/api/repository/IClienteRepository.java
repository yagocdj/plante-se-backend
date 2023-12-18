package com.plantese.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plantese.api.models.Cliente;

//@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Método para localizar um cliente a partir do seu endereço de email.
     * @param email o email do cliente a ser localizado.
     * @return o cliente cujo email é igual ao passado como argumento deste método.
     */
    public Cliente findClienteByEmail(String email);
}
