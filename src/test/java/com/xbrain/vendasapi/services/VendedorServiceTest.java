package com.xbrain.vendasapi.services;

import com.xbrain.vendasapi.controllers.dto.VendasVendedorResponse;
import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Venda;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.exceptions.VendedorNaoEncontradoException;
import com.xbrain.vendasapi.repositories.VendaRepository;
import com.xbrain.vendasapi.repositories.VendedorRepository;
import com.xbrain.vendasapi.services.impl.VendedorServiceImpl;
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
import java.util.List;
import java.util.Optional;

class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private VendaRepository vendaRepository;

    private VendedorService vendedorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        vendedorService = new VendedorServiceImpl(vendedorRepository, vendaRepository);
    }

    @Test
    @DisplayName("dado VendaRequest " +
            "quando cadastrar " +
            "então deve retornar o Vendedor e salvar no banco")
    void teste() {
        VendedorRequest vendedorRequest = new VendedorRequest("Renan");
        Mockito.when(vendedorRepository.save(Mockito.any())).thenReturn(new Vendedor("Pedro"));
        Vendedor vendedor = vendedorService.cadastrar(vendedorRequest);
        Assertions.assertEquals(new Vendedor("Pedro"), vendedor);
        Mockito.verify(vendedorRepository).save(new Vendedor("Renan"));
    }

    @Test
    @DisplayName("dado um ID " +
            "quando buscar " +
            "então deve retornar o Vendedor")
    void teste1() {
        Mockito.when(vendedorRepository.findById(Mockito.any())).thenReturn(Optional.of(new Vendedor("Pedro")));
        Vendedor vendedor = vendedorService.buscarPorId(2L);
        Assertions.assertEquals(new Vendedor("Pedro"), vendedor);
        Mockito.verify(vendedorRepository).findById(2L);
    }

    @Test
    @DisplayName("dado um ID " +
            "quando buscar e não existir " +
            "então deve retornar uma exceção")
    void teste2() {
        Mockito.when(vendedorRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(VendedorNaoEncontradoException.class, () -> vendedorService.buscarPorId(2L));
        Mockito.verify(vendedorRepository).findById(2L);

    }

    @Test
    @DisplayName("dado um período " +
            "quando chamar informações da venda " +
            "então deve retornar uma lista de vendedores")
    void teste3() {
        Mockito.when(vendedorRepository.findAll()).thenReturn(List.of(new Vendedor(1L, "Matheus"), new Vendedor(2L, "Maria")));
        Mockito.when(vendaRepository.findByVendedorIdAndDataHoraVendaBetween(Mockito.eq(1L), Mockito.any(), Mockito.any())).thenReturn(List.of(new Venda(),new Venda(), new Venda()));
        Mockito.when(vendaRepository.findByVendedorIdAndDataHoraVendaBetween(Mockito.eq(2L), Mockito.any(), Mockito.any())).thenReturn(List.of(new Venda(), new Venda()));
        List<VendasVendedorResponse> listaVendasVendedor = vendedorService.informacoesVenda(LocalDate.of(2024,4,13), LocalDate.of(2024, 4, 20));
        Assertions.assertEquals(new VendasVendedorResponse("Matheus", 3, new BigDecimal("0.43")), listaVendasVendedor.getFirst());
        Assertions.assertEquals(new VendasVendedorResponse("Maria", 2, new BigDecimal("0.29")), listaVendasVendedor.get(1));
        Mockito.verify(vendedorRepository).findAll();
        Mockito.verify(vendaRepository).findByVendedorIdAndDataHoraVendaBetween(1L, LocalDateTime.of(LocalDate.of(2024,4,13), LocalTime.MIN), LocalDateTime.of(LocalDate.of(2024,4,20), LocalTime.MAX));
        Mockito.verify(vendaRepository).findByVendedorIdAndDataHoraVendaBetween(2L, LocalDateTime.of(LocalDate.of(2024,4,13), LocalTime.MIN), LocalDateTime.of(LocalDate.of(2024,4,20), LocalTime.MAX));
    }
}

