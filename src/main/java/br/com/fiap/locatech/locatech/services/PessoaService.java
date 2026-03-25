package br.com.fiap.locatech.locatech.services;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.dtos.PessoaRequestDTO;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundExecption;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.repositories.PessoaRepository;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllPessoa(int page, int size) {
        int offset = (page - 1) * size;
        return this.pessoaRepository.findAll(size, offset);
    }

    public Optional<Pessoa> findPessoaById(Long id) {
        return Optional.ofNullable(this.pessoaRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundExecption("Pessoa não encontrada")));
    }

    public void save(PessoaRequestDTO pessoa) {
        var entityPessoa = new Pessoa(pessoa);
        var save = this.pessoaRepository.save(entityPessoa);
        Assert.state(save == 1, "Erro ao salvar pessoa " + pessoa.nome());
    }

    public void update(PessoaRequestDTO pessoa, Long id) {
        var entityPessoa = new Pessoa(pessoa);
        var update = this.pessoaRepository.update(entityPessoa, id);
        if (update == 0) {
            throw new RuntimeException("Pessoa não encontrada.");
        }
    }

    public void delete(Long id) {
        var delete = this.pessoaRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Pessoa não encontrada.");
        }
    }
}
