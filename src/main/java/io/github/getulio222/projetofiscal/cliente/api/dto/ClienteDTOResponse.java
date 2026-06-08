package io.github.getulio222.projetofiscal.cliente.api.dto;

public record ClienteDTOResponse(Long id,
                                 String nome,
                                 String cpf,
                                 String cidade) {
}
