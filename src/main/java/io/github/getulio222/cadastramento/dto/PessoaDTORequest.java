package io.github.getulio222.cadastramento.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaDTORequest(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotBlank
        @CPF
        String cpf,

        @Size(max = 50)
        String cidade,

        @NotNull
        Long cd_estado
) {
}
