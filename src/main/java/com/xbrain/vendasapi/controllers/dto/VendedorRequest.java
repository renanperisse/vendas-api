package com.xbrain.vendasapi.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VendedorRequest (
        @NotBlank(message = "O nome do vendedor não pode estar vazio.")
        @NotNull(message = "O nome do vendedor não pode ser nulo.") String nome) {
}
