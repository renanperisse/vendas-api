package com.xbrain.vendasapi.services.impl;

import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.repositories.VendedorRepository;
import com.xbrain.vendasapi.services.VendedorService;
import org.springframework.stereotype.Service;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public Vendedor cadastrar(VendedorRequest vendedorRequest) {
        return vendedorRepository.save(new Vendedor(vendedorRequest.nome()));
    }
}
