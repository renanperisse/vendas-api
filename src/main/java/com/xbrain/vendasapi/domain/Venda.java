package com.xbrain.vendasapi.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "TB_VENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraVenda;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    public Venda() {
    }

    public Venda(BigDecimal valor, Vendedor vendedor, LocalDateTime dataHoraVenda) {
        this.valor = valor;
        this.vendedor = vendedor;
        this.dataHoraVenda = dataHoraVenda;
    }

    public Venda(LocalDateTime dataHoraVenda, Vendedor vendedor, BigDecimal valor, Long id) {
        this.dataHoraVenda = dataHoraVenda;
        this.vendedor = vendedor;
        this.valor = valor;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setDataHoraVenda(LocalDateTime dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id) && Objects.equals(dataHoraVenda, venda.dataHoraVenda) && Objects.equals(valor, venda.valor) && Objects.equals(vendedor, venda.vendedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataHoraVenda, valor, vendedor);
    }
}
