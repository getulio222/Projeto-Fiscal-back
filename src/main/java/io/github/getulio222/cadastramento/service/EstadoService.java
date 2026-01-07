package io.github.getulio222.cadastramento.service;

import io.github.getulio222.cadastramento.dto.EstadoDTOResponse;
import io.github.getulio222.cadastramento.entity.Estado;
import io.github.getulio222.cadastramento.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<EstadoDTOResponse> listar() {
        return estadoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public EstadoDTOResponse buscarPorId(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        return mapToDTO(estado);
    }

    private EstadoDTOResponse mapToDTO(Estado estado) {
        return new EstadoDTOResponse(
                estado.getId(),
                estado.getNome()
        );
    }
}