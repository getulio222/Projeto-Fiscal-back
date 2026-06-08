package io.github.getulio222.projetofiscal.cliente.api.dto;

import io.github.getulio222.projetofiscal.cliente.domain.TipoPessoa;

public record ClienteDTOResponse(

        Long id,
        TipoPessoa tipoPessoa,
        String nomeRazaoSocial,
        String cpfCnpj,
        String pais,
        String uf,
        String cidade,
        String endereco,
        String telefone

) {
}