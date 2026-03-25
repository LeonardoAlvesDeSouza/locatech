package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AluguelRequestDTO(
        @Schema(description = "Id da pessoa que está alugando o veículo")
        @NotNull(message = "O id da pessoa não pode ser nulo")
        Long pessoaId,

        @Schema(description = "Id do veículo que será alugado")
        @NotNull(message = "O id do veículo não pode ser nulo.")
        Long veiculoId,

        @Schema(description = "Data de início do aluguel do veículo")
        @NotNull(message = "É necessário uma data de início.")
        LocalDate dataInicio,

        @Schema(description = "Data fim para a entrega do veículo")
        @NotNull(message = "É necessário uma data final.")
        LocalDate dataFim
) {

}
