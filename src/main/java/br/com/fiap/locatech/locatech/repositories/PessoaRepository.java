package br.com.fiap.locatech.locatech.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.entities.Pessoa;

public interface PessoaRepository {
    Optional<Pessoa> findById(Long id);
    List<Pessoa> findAll(int size, int offset);
    Integer save(Pessoa pessoa);
    Integer update(Pessoa pessoa, Long id);
    Integer delete(Long id);
}
