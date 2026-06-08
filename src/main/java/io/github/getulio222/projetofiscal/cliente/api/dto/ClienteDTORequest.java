package io.github.getulio222.projetofiscal.cliente.api.dto;

import io.github.getulio222.projetofiscal.cliente.domain.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteDTORequest(

        @NotNull
        TipoPessoa tipoPessoa,

        @NotBlank
        @Size(max = 200)
        String nomeRazaoSocial,

        @NotBlank
        @Size(max = 14)
        String cpfCnpj,

        @NotBlank
        @Size(max = 100)
        String pais,

        @NotBlank
        @Size(max = 2)
        String uf,

        @NotBlank
        @Size(max = 100)
        String cidade,

        @NotBlank
        @Size(max = 300)
        String endereco,

        @Size(max = 20)
        String telefone

) {
}