package com.xbrain.vendasapi.repositories;

import com.xbrain.vendasapi.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
