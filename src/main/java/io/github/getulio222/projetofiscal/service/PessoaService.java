package io.github.getulio222.projetofiscal.service;

import io.github.getulio222.projetofiscal.dto.pessoa.PessoaDTORequest;
import io.github.getulio222.projetofiscal.dto.pessoa.PessoaDTOResponse;
import io.github.getulio222.projetofiscal.entity.Pessoa;
import io.github.getulio222.projetofiscal.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private static final String MSG_DADOS_NAO_LOCALIZADO = "Dados não localizado, tente novamente.";

    private final PessoaRepository pessoaRepository;


    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    public PessoaDTOResponse salvar(PessoaDTORequest dto) {


        if (pessoaRepository.existsByCpf(dto.cpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }



        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setCidade(dto.cidade());


        Pessoa saved = pessoaRepository.save(pessoa);
        return mapToResponse(saved);
    }


    public List<PessoaDTOResponse> listar() {
        return pessoaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public PessoaDTOResponse buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));
        return mapToResponse(pessoa);
    }


    public PessoaDTOResponse buscarPorCpf(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));
        return mapToResponse(pessoa);
    }

    public List<PessoaDTOResponse> buscarPorNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PessoaDTOResponse atualizar(Long id, PessoaDTORequest dto) {

        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));

        String novoCpf = dto.cpf();
        if (pessoaRepository.existsByCpfAndIdNot(dto.cpf(), id)) {
            throw new RuntimeException("CPF já cadastrado");
        }

        pessoa.setNome(dto.nome());
        pessoa.setCpf(novoCpf);
        pessoa.setCidade(dto.cidade());
        Pessoa updated = pessoaRepository.save(pessoa);
        return mapToResponse(updated);
    }

    public void excluir(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));
        pessoaRepository.delete(pessoa);
    }

    private PessoaDTOResponse mapToResponse(Pessoa pessoa) {
        return new PessoaDTOResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getCidade()
        );
    }
}