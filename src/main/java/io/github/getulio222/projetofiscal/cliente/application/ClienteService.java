package io.github.getulio222.projetofiscal.cliente.application;

import io.github.getulio222.projetofiscal.cliente.api.dto.ClienteDTORequest;
import io.github.getulio222.projetofiscal.cliente.api.dto.ClienteDTOResponse;
import io.github.getulio222.projetofiscal.cliente.domain.Cliente;
import io.github.getulio222.projetofiscal.cliente.infra.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private static final String MSG_DADOS_NAO_LOCALIZADO = "Dados não localizado, tente novamente.";

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTOResponse salvar(ClienteDTORequest dto) {

        if (clienteRepository.existsByCpfCnpj(dto.cpfCnpj())) {
            throw new RuntimeException("CPF/CNPJ já cadastrado");
        }

        Cliente cliente = new Cliente();
        preencherCliente(cliente, dto);

        Cliente saved = clienteRepository.save(cliente);
        return mapToResponse(saved);
    }

    public List<ClienteDTOResponse> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ClienteDTOResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));

        return mapToResponse(cliente);
    }

    public ClienteDTOResponse buscarPorCpfCnpj(String cpfCnpj) {
        Cliente cliente = clienteRepository.findByCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));

        return mapToResponse(cliente);
    }

    public List<ClienteDTOResponse> buscarPorNome(String nomeRazaoSocial) {
        return clienteRepository.findByNomeRazaoSocialContainingIgnoreCase(nomeRazaoSocial)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ClienteDTOResponse atualizar(Long id, ClienteDTORequest dto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));

        if (clienteRepository.existsByCpfCnpjAndIdNot(dto.cpfCnpj(), id)) {
            throw new RuntimeException("CPF/CNPJ já cadastrado");
        }

        preencherCliente(cliente, dto);

        Cliente updated = clienteRepository.save(cliente);
        return mapToResponse(updated);
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_DADOS_NAO_LOCALIZADO));

        clienteRepository.delete(cliente);
    }

    private void preencherCliente(Cliente cliente, ClienteDTORequest dto) {
        cliente.setTipoPessoa(dto.tipoPessoa());
        cliente.setNomeRazaoSocial(dto.nomeRazaoSocial());
        cliente.setCpfCnpj(dto.cpfCnpj());
        cliente.setPais(dto.pais());
        cliente.setUf(dto.uf());
        cliente.setCidade(dto.cidade());
        cliente.setEndereco(dto.endereco());
        cliente.setTelefone(dto.telefone());
    }

    private ClienteDTOResponse mapToResponse(Cliente cliente) {
        return new ClienteDTOResponse(
                cliente.getId(),
                cliente.getTipoPessoa(),
                cliente.getNomeRazaoSocial(),
                cliente.getCpfCnpj(),
                cliente.getPais(),
                cliente.getUf(),
                cliente.getCidade(),
                cliente.getEndereco(),
                cliente.getTelefone()
        );
    }
}