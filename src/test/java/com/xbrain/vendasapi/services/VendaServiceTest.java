package com.xbrain.vendasapi.services;

import com.xbrain.vendasapi.controllers.dto.VendaRequest;
import com.xbrain.vendasapi.domain.Venda;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.repositories.VendaRepository;
import com.xbrain.vendasapi.services.impl.VendaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class VendaServiceTest {

    @Mock
    private VendedorService vendedorService;

    @Mock
    private VendaRepository vendaRepository;

    private VendaService vendaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        vendaService = new VendaServiceImpl(vendaRepository, vendedorService);
    }

    @Test
    @DisplayName("dado uma VendaRequest " +
            "quando gerar venda " +
            "então deve cadastrar e retornar Venda")
    void teste() {
        Mockito.when(vendedorService.buscarPorId(Mockito.any())).thenReturn(new Vendedor(1L,"Renan"));
        Mockito.when(vendaRepository.save(Mockito.any())).thenReturn(new Venda(new BigDecimal("150.0"), new Vendedor(), LocalDateTime.of(LocalDate.now(), LocalTime.MIN)));
        Venda venda = vendaService.gerarVenda(new VendaRequest(1L, new BigDecimal("200.0")));
        Assertions.assertEquals(new Venda(new BigDecimal("150.0"), new Vendedor(), LocalDateTime.of(LocalDate.now(), LocalTime.MIN)), venda);
        Mockito.verify(vendedorService).buscarPorId(1L);
        Mockito.verify(vendaRepository).save(Mockito.any());
    }

}