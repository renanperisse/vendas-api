package com.xbrain.vendasapi.services.impl;

import com.xbrain.vendasapi.controllers.dto.VendaRequest;
import com.xbrain.vendasapi.domain.Venda;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.repositories.VendaRepository;
import com.xbrain.vendasapi.services.VendaService;
import com.xbrain.vendasapi.services.VendedorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;
    private final VendedorService vendedorService;

    public VendaServiceImpl(VendaRepository vendaRepository, VendedorService vendedorService) {
        this.vendaRepository = vendaRepository;
        this.vendedorService = vendedorService;
    }

    @Override
    public Venda gerarVenda(VendaRequest vendaRequest) {
        Vendedor vendedor = vendedorService.buscarPorId(vendaRequest.idVendedor());
        Venda venda = new Venda(vendaRequest.valor(), vendedor, LocalDateTime.now());
        return vendaRepository.save(venda);
    }
}
