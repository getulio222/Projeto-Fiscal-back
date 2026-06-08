package io.github.getulio222.projetofiscal.seguranca.api.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTORequest(@NotBlank  String usuario,
                              @NotBlank  String senha) {
}
