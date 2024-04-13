package com.xbrain.vendasapi.controllers.dto;

import java.math.BigDecimal;

public record VendasVendedorResponse(
        String nome,
        int totalVendas,
        BigDecimal mediaVendasDiaria) {
}
