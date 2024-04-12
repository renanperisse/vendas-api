package com.xbrain.vendasapi.controllers.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VendaRequest(
        @NotNull(message = "O ID do vendedor não pode ser nulo.")
        Long idVendedor,
        @Min(value = 1L, message = "O valor mínimo da venda é de R$ 1,00")
        @NotNull(message = "O valor da venda não pode ser nula.")
        BigDecimal valor) {

}
