package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VeiculoRequestDTO(
        @Schema(description = "Deve ser inserido uma marca para o veículo")
        @NotNull(message = "A marca não pode ser nulo")
        String marca,

        @Schema(description = "Deve informar o modelo do veículo")
        @NotNull(message = "O modelo do veículo não pode ser nulo")
        String modelo,

        @Schema(description = "Obrigatório informar a placa do veículo")
        @NotNull(message = "Placa não pode ser nulo")
        String placa,

        @Schema(description = "O veículo deve conter um nome")
        @NotNull(message = "O ano do veículo não pode ser nulo")
        int ano,

        @Schema(description = "Uma cor existente deve ser informada para o veículo")
        @NotNull(message = "A cor do veículo não pode ser nulo")
        String cor,

        @Schema(description = "É obrigatório informar o valor da diária do veículo")
        @NotNull(message = "Valor da diária não pode ser nulo")
        BigDecimal valorDiaria
) {
}
