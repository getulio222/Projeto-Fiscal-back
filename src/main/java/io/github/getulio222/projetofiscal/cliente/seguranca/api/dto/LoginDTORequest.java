package io.github.getulio222.projetofiscal.cliente.seguranca.api.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTORequest(@NotBlank  String usuario,
                              @NotBlank  String senha) {
}
