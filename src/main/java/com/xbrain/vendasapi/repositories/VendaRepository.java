package com.xbrain.vendasapi.repositories;

import com.xbrain.vendasapi.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
