package io.github.getulio222.cadastramento.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTORequest(@NotBlank  String usuario,
                              @NotBlank  String senha) {
}
