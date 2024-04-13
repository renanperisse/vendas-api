package com.xbrain.vendasapi.controllers;

import com.xbrain.vendasapi.controllers.dto.VendasVendedorResponse;
import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.services.VendedorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendedorController.class)
class VendedorControllerTest {

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("dado um VendedorRequest " +
            "quando for chamado o endpoint de cadastrar Vendedor" +
            "então deve chamar a classe de serviço e retornar Status 201")
    void teste() throws Exception {
        Mockito.when(vendedorService.cadastrar(Mockito.any())).thenReturn(new Vendedor(1L, "Renan"));
        mockMvc.perform(post("/vendedores")
                .content("{\n" + "\"nome\": \"Renan\"\n" + "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Mockito.verify(vendedorService).cadastrar(new VendedorRequest("Renan"));
    }

    @Test
    @DisplayName("dado um VendedorRequest inválido" +
            "quando for chamado o endpoint de cadastrar Vendedor" +
            "então deve retornar Status 400")
    void teste1() throws Exception {
        mockMvc.perform(post("/vendedores")
                        .content("{\n" + "\"nome\": \"\"\n" + "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("dado um período (início e fim) " +
            "quando for chamado o endpoint de listar as vendas e os vendedores" +
            "então deve chamar a lista de serviço e retornar Status 200")
    void teste2() throws Exception {
        Mockito.when(vendedorService.informacoesVenda(Mockito.any(), Mockito.any())).thenReturn(List.of(new VendasVendedorResponse("Renan", 3, new BigDecimal("0.75"))));
        mockMvc.perform(get("/vendedores/total-vendas?dataInicio=12/04/2024&dataFim=15/04/2024"))
                .andExpect(status().isOk());
        Mockito.verify(vendedorService).informacoesVenda(LocalDate.of(2024, 4, 12), LocalDate.of(2024, 4, 15));
    }

}