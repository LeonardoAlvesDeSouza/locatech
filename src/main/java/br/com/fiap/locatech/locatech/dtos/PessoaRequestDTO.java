package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record PessoaRequestDTO(
        @Schema(description = "Deve ser inserido um nome para pessoa")
        @NotNull(message = "O nome da pessoa não pode ser nulo")
        String nome,

        @Schema(description = "Deve ser inserido um CPF para pessoa")
        @NotNull(message = "O CPF da pessoa não pode ser nulo")
        String cpf,

        String telefone,

        String email
) {

}
