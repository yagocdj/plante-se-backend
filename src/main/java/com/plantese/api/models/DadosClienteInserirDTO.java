package com.plantese.api.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosClienteInserirDTO(
        @NotBlank String nome,
        @NotBlank String cpf,
        @Email(message = "Email inválido!") String email,
        @NotBlank String endereco,
        @NotBlank String telefone,
        @NotBlank String senha
) { }
