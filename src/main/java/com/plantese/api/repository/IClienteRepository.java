package com.plantese.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plantese.api.models.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    /**
     * Método para localizar um cliente a partir do seu endereço de email.
     * @param email o email do cliente a ser localizado.
     * @return o cliente cujo email é igual ao passado como argumento deste método.
     */
    public Cliente findClienteByEmail(String email);

    /**
     * Método para localizar um cliente a partir do seu CPF.
     * @param cpf o CPF do cliente a ser localizado
     * @return o cliente cujo CPF é igual ao passado como argumento.
     */
    public Cliente findClienteByCpf(String cpf);
}
