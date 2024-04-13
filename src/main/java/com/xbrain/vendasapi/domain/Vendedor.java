package com.xbrain.vendasapi.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "TB_VENDEDOR")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "vendedor")
    private List<Venda> vendas;

    public Vendedor() {
    }

    public Vendedor(String nome) {
        this.nome = nome;
    }

    public Vendedor(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(id, vendedor.id) && Objects.equals(nome, vendedor.nome) && Objects.equals(vendas, vendedor.vendas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, vendas);
    }
}
