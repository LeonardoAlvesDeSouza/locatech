package br.com.fiap.locatech.locatech.services;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.dtos.VeiculoRequestDTO;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundExecption;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> findAllVeiculos(int page, int size) {
        int offset = (page - 1) * size;
        return this.veiculoRepository.findAll(size, offset);
    }

    public Optional<Veiculo> findVeiculoById(Long id) {
        return Optional.ofNullable(this.veiculoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundExecption("Veículo não encontrado")));
    }

    public void saveVeiculo(VeiculoRequestDTO veiculo) {
        var entityVeiculo = new Veiculo(veiculo);
        var save = this.veiculoRepository.save(entityVeiculo);
        Assert.state(save == 1, "Erro ao salvar veículo " + veiculo.placa());
    }

    public void updateVeiculo(VeiculoRequestDTO veiculo, Long id) {
        var entityVeiculo = new Veiculo(veiculo);
        var update = this.veiculoRepository.update(entityVeiculo, id);
        if (update == 0) {
            throw new RuntimeException("Veículo não encontrado");
        }
    }

    public void deleteVeiculo(Long id) {
        var delete = this.veiculoRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Veículo não encontrado");
        }
    }
}
