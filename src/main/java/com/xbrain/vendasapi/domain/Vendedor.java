package com.xbrain.vendasapi.domain;

import jakarta.persistence.*;

import java.util.List;

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

}
