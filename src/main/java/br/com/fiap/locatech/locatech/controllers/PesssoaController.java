package br.com.fiap.locatech.locatech.controllers;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.dtos.PessoaRequestDTO;
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

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoa", description = "Controller para crud de pessoas")
public class PesssoaController {
    private static final Logger logger = LoggerFactory.getLogger(PesssoaController.class);

    private final PessoaService pessoaService;

    public PesssoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(
            description = "Busca todos as pessoas paginado",
            summary = "Busca de pessoas",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
        @RequestParam("page") int page,
        @RequestParam("size") int size
    ) {
        logger.info("/pessoas");
        var pessoas = this.pessoaService.findAllPessoa(page, size);
        return ResponseEntity.ok(pessoas);
    }

    @Operation(
            description = "Busca pessoa pelo id",
            summary = "Busca de pessoa",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(
        @PathVariable("id") Long id
    ) {
        logger.info("/pessoas/" + id);
        var pessoa = this.pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(
            description = "Adiciona uma pessoa",
            summary = "Inclui uma pessoa",
            responses = {
                    @ApiResponse(description = "Criado", responseCode = "201")
            }
    )
    @PostMapping
    public ResponseEntity<Void> savePessoa(
       @Valid @RequestBody PessoaRequestDTO pessoa
    ) {
        logger.info("POST -> /pessoas");
        this.pessoaService.save(pessoa);
        return ResponseEntity.status(201).build();
    }

    @Operation(
            description = "Alteração de pessoa",
            summary = "Realiza atualizações no cadastro da pessoa",
            responses = {
                    @ApiResponse(description = "Alterado", responseCode = "204")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
        @PathVariable("id") Long id,
        @Valid @RequestBody PessoaRequestDTO pessoa
    ) {
        logger.info("PUT -> pesssoa: " + pessoa.nome() + ", id = " + id);
        this.pessoaService.update(pessoa, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Exclui uma pessoa",
            summary = "Deleta uma pessoa existente",
            responses = {
                    @ApiResponse(description = "Excluído", responseCode = "204")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(
        @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /pessoas/" + id);
        this.pessoaService.delete(id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    
}
