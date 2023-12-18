package com.plantese.api.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosClienteInserirDTO(
        @NotBlank String nome,
        @NotBlank @Size(min = 11, max = 11) String cpf,
        @Email(
                regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Email inválido!") String email,
        @NotBlank String endereco,
        @NotBlank String telefone,
        @NotBlank String senha
) { }
