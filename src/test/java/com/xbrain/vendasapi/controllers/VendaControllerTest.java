package com.xbrain.vendasapi.controllers;

import com.xbrain.vendasapi.controllers.dto.VendaRequest;
import com.xbrain.vendasapi.domain.Venda;
import com.xbrain.vendasapi.services.VendaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendaController.class)
class VendaControllerTest {

    @MockBean
    private VendaService vendaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("dado uma VendaRequest " +
            "quando for chamar o endpoint de gerar a venda" +
            "então deve chamar a classe de serviço e retornar Status 201")
    void teste() throws Exception {
        Mockito.when(vendaService.gerarVenda(Mockito.any())).thenReturn(new Venda(null, null, null, 1L));
        mockMvc.perform(post("/venda")
                .content("{\n" +
                        "\"idVendedor\": 6,\n" +
                        "\"valor\": 6.0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Mockito.verify(vendaService).gerarVenda(new VendaRequest(6L, new BigDecimal("6.0")));
    }

    @Test
    @DisplayName("dado uma VendaRequest inválida" +
            "quando for chamado o endpoint de gerar Venda" +
            "então deve retornar Status 400")
    void teste1() throws Exception {
        mockMvc.perform(post("/venda")
                        .content("{\n" +
                                "\"idVendedor\": null,\n" +
                                "\"valor\": 0.0\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}