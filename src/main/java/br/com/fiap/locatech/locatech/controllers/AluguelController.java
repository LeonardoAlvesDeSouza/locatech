package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.services.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
@Tag(name = "Aluguel", description = "Controller para crud de alugueis")
public class AluguelController {
    private static final Logger logger = LoggerFactory.getLogger(AluguelController.class);

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @Operation(
            description = "Busca todos os alugueis paginado",
            summary = "Busca de alugueis",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAlugueis(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("/alugueis");
        var alugueis = this.aluguelService.findAllAlugueis(page, size);
        return ResponseEntity.ok(alugueis);
    }

    @Operation(
            description = "Busca aluguel pelo id",
            summary = "Busca de alugueis",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(
            @PathVariable("id") Long id
    ) {
        logger.info("/alugueis/" + id);
        var aluguel = this.aluguelService.findAluguelById(id);
        return ResponseEntity.ok(aluguel);
    }

    @Operation(
            description = "Adiciona um aluguel",
            summary = "Inclui um aluguel",
            responses = {
                    @ApiResponse(description = "Criado", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveAluguel(
          @Valid @RequestBody AluguelRequestDTO aluguel
    ) {
        logger.info("POST -> /alugueis");
        this.aluguelService.save(aluguel);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Alteração de aluguel",
            summary = "Realiza atualizações no cadastro da aluguel",
            responses = {
                    @ApiResponse(description = "Alterado", responseCode = "204")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @PathVariable("id") Long id,
            @Valid @RequestBody AluguelRequestDTO aluguel
    ) {
        logger.info("PUT -> id = " + id);
        this.aluguelService.update(aluguel, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Exclui um aluguel",
            summary = "Deleta um aluguel existente",
            responses = {
                    @ApiResponse(description = "Excluído", responseCode = "204")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(
            @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /alugueis/" + id);
        this.aluguelService.delete(id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

}
