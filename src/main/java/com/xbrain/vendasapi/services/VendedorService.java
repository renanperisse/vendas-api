package com.xbrain.vendasapi.services;

import com.xbrain.vendasapi.controllers.dto.VendasVendedorResponse;
import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;

import java.time.LocalDate;
import java.util.List;

public interface VendedorService {

    Vendedor cadastrar(VendedorRequest vendedorRequest);
    Vendedor buscarPorId(Long id);
    List<VendasVendedorResponse> informacoesVenda(LocalDate dataInicio, LocalDate dataFim);
}