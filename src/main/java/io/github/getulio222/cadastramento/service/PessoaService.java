package io.github.getulio222.cadastramento.service;

import io.github.getulio222.cadastramento.dto.PessoaDTORequest;
import io.github.getulio222.cadastramento.dto.PessoaDTOResponse;
import io.github.getulio222.cadastramento.entity.Estado;
import io.github.getulio222.cadastramento.entity.Pessoa;
import io.github.getulio222.cadastramento.repository.EstadoRepository;
import io.github.getulio222.cadastramento.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final EstadoRepository estadoRepository;

    public PessoaService(PessoaRepository pessoaRepository,
                         EstadoRepository estadoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.estadoRepository = estadoRepository;
    }

    public Pessoa salvar(PessoaDTORequest dto) {

        if (pessoaRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        Estado estado = estadoRepository.findById(dto.cd_estado())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setCidade(dto.cidade());
        pessoa.setEstado(estado);
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaDTOResponse> listar() {
        return pessoaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PessoaDTOResponse buscarPorId(Long id) {
         Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
         return mapToResponse(pessoa);
    }

    public PessoaDTOResponse atualizar(Long id, PessoaDTORequest dto) {

        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Estado estado = estadoRepository.findById(dto.cd_estado())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setCidade(dto.cidade());
        pessoa.setEstado(estado);

        Pessoa pessoaUpdate = pessoaRepository.save(pessoa);
        return mapToResponse(pessoaUpdate);
    }

    public void excluir(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoaRepository.delete(pessoa);
    }
    public PessoaDTOResponse buscarPorCpf(String cpf) {

        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
                .orElseThrow(() ->
                        new RuntimeException("Dados não localizado, tente novamente.")
                );

        return mapToResponse(pessoa);
    }

    public List<PessoaDTOResponse> buscarPorNome(String nome) {

        List<Pessoa> pessoas = pessoaRepository
                .findByNomeContainingIgnoreCase(nome);

        if (pessoas.isEmpty()) {
            throw new RuntimeException("Dados não localizado, tente novamente.");
        }

        return pessoas.stream()
                .map(this::mapToResponse)
                .toList();
    }


    private PessoaDTOResponse mapToResponse(Pessoa pessoa) {
        return new PessoaDTOResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getCidade(),
                pessoa.getEstado());
    }
}
