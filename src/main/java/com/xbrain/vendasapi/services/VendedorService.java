package com.xbrain.vendasapi.services;

import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;

public interface VendedorService {

    Vendedor cadastrar(VendedorRequest vendedorRequest);
    Vendedor buscarPorId(Long id);
}