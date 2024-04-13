package com.xbrain.vendasapi.controllers;

import com.xbrain.vendasapi.controllers.dto.VendasVendedorResponse;
import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.services.VendedorService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("/total-vendas")
    public ResponseEntity<List<VendasVendedorResponse>> getDadosVendasVendedores(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {
        return ResponseEntity.ok(vendedorService.informacoesVenda(dataInicio, dataFim));
    }
}
