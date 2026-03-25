package br.com.fiap.locatech.locatech.entities;

import java.math.BigDecimal;

import br.com.fiap.locatech.locatech.dtos.VeiculoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Veiculo {
    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private int ano;
    private String cor;
    private BigDecimal valorDiaria;

    public Veiculo(VeiculoRequestDTO veiculo) {
        this.marca = veiculo.marca();
        this.modelo = veiculo.modelo();
        this.placa = veiculo.placa();
        this.ano = veiculo.ano();
        this.cor = veiculo.cor();
        this.valorDiaria = veiculo.valorDiaria();
    }

}
