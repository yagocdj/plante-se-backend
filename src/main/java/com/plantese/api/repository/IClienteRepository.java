package com.plantese.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.plantese.api.models.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    public Cliente findByEmail(String email);

    // TODO - caso haja mais consultas a serem feitas, elas devem ser feitas aqui
}
