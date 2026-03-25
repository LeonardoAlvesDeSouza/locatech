package br.com.fiap.locatech.locatech.controllers;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.dtos.VeiculoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.VeiculoService;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículo", description = "Controller para crud de veículos")
public class VeiculoController {

    private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(
            description = "Busca todos os veículos paginados",
            summary = "Busca de veículos",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ) {
        logger.info("/veiculos");
        var veiculos = this.veiculoService.findAllVeiculos(page, size);
        return ResponseEntity.ok(veiculos);
    }

    @Operation(
            description = "Busca veículo pelo id",
            summary = "Busca de veículo",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(
        @PathVariable("id") Long id
    ) {
        logger.info("/veiculos/" + id);
        var veiculo = this.veiculoService.findVeiculoById(id);
        return ResponseEntity.ok(veiculo);
    }

    @Operation(
            description = "Adiciona um veículo",
            summary = "Inclui um veículo",
            responses = {
                    @ApiResponse(description = "Criado", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveVeiculo(
        @Valid @RequestBody VeiculoRequestDTO veiculo
    ) {
        logger.info("POST -> /veiculos");
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Alteração de veículo",
            summary = "Realiza atualizações no cadastro de veículo",
            responses = {
                    @ApiResponse(description = "Alterado", responseCode = "204")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
        @PathVariable("id") Long id,
        @Valid @RequestBody VeiculoRequestDTO veiculo
    ) {
        logger.info("PUT -> /veiculos/" + id);
        this.veiculoService.updateVeiculo(veiculo, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Exclui um veículo",
            summary = "Deleta um veículo existente",
            responses = {
                    @ApiResponse(description = "Excluído", responseCode = "204")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(
        @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /veiculos/" + id);
        this.veiculoService.deleteVeiculo(id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }
    
}
