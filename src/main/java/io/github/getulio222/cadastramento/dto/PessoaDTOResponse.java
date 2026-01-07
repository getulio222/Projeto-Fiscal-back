package io.github.getulio222.cadastramento.dto;

import io.github.getulio222.cadastramento.entity.Estado;

public record PessoaDTOResponse(Long id,
                                String nome,
                                String cpf,
                                String cidade,
                                Estado estado) {
}
