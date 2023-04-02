package com.sistema.votacao.domain.entities;

public class Pauta {

    public Pauta() {
    }

    public String getNome() {
        return nome;
    }

    public Pauta(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String nome;
}
