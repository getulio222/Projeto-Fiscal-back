package io.github.getulio222.projetofiscal.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginDTORequest(@NotBlank  String usuario,
                              @NotBlank  String senha) {
}
