package io.github.getulio222.projetofiscal.dto.pessoa;


import jakarta.validation.constraints.NotBlank;
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
        String cidade


) {
}
