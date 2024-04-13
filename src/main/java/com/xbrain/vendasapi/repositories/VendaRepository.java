package com.xbrain.vendasapi.repositories;

import com.xbrain.vendasapi.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByVendedorIdAndDataHoraVendaBetween(Long vendedorId, LocalDateTime dataInicio, LocalDateTime dataFim);
}
