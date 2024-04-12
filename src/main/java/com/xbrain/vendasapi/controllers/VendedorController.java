package com.xbrain.vendasapi.controllers;

import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.services.VendedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarVendedor(@RequestBody @Valid VendedorRequest vendedorRequest) {
        Vendedor vendedor = vendedorService.cadastrar(vendedorRequest);
        return ResponseEntity.created(URI.create("/vendedores/" + vendedor.getId())).build();
    }
}
