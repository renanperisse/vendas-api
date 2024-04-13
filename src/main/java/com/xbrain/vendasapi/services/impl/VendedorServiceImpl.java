package com.xbrain.vendasapi.services.impl;

import com.xbrain.vendasapi.controllers.dto.VendasVendedorResponse;
import com.xbrain.vendasapi.controllers.dto.VendedorRequest;
import com.xbrain.vendasapi.domain.Vendedor;
import com.xbrain.vendasapi.exceptions.VendedorNaoEncontradoException;
import com.xbrain.vendasapi.repositories.VendaRepository;
import com.xbrain.vendasapi.repositories.VendedorRepository;
import com.xbrain.vendasapi.services.VendedorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;
    private final VendaRepository vendaRepository;

    public VendedorServiceImpl(VendedorRepository vendedorRepository, VendaRepository vendaRepository) {
        this.vendedorRepository = vendedorRepository;
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Vendedor cadastrar(VendedorRequest vendedorRequest) {
        return vendedorRepository.save(new Vendedor(vendedorRequest.nome()));
    }

    @Override
    public Vendedor buscarPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new VendedorNaoEncontradoException("Vendedor não encontrado para o id " + id));
    }

    @Override
    public List<VendasVendedorResponse> informacoesVenda(LocalDate dataInicio, LocalDate dataFim) {
        int intervaloDeDias = dataInicio.until(dataFim).getDays();
        List<Vendedor> vendedorList = vendedorRepository.findAll();
        List<VendasVendedorResponse> vendasVendedorResponseList = new ArrayList<>();
        for(Vendedor vendedor : vendedorList) {
            int totalVendas = vendaRepository.findByVendedorIdAndDataHoraVendaBetween(vendedor.getId(),
                    LocalDateTime.of(dataInicio, LocalTime.MIN),
                    LocalDateTime.of(dataFim, LocalTime.MAX)).size();
            BigDecimal mediaVendasDiaria = new BigDecimal(totalVendas).divide(new BigDecimal(intervaloDeDias), 2, RoundingMode.HALF_EVEN);
            vendasVendedorResponseList.add(new VendasVendedorResponse(vendedor.getNome(), totalVendas, mediaVendasDiaria));
        }
        return vendasVendedorResponseList;
    }
}
