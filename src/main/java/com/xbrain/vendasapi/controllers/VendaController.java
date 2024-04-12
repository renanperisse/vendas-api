package com.xbrain.vendasapi.controllers;

import com.xbrain.vendasapi.controllers.dto.VendaRequest;
import com.xbrain.vendasapi.domain.Venda;
import com.xbrain.vendasapi.services.VendaService;
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
@RequestMapping("/venda")
public class VendaController {


    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Void> gerarVenda(@RequestBody @Valid VendaRequest vendaRequest) {
        Venda venda = vendaService.gerarVenda(vendaRequest);
        return ResponseEntity.created(URI.create("/venda/" + venda.getId())).build();
    }
}
