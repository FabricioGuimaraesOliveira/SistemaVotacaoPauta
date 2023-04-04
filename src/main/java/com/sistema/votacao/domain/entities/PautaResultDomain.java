package com.sistema.votacao.domain.entities;

public class PautaResultDomain {
    private Long pauta_id;
    private String nome;

    public PautaResultDomain(Long pauta_id, String nome, Integer qtd_sim, Integer qtd_nao) {
        this.pauta_id = pauta_id;
        this.nome = nome;
        this.qtd_sim = qtd_sim;
        this.qtd_nao = qtd_nao;
    }

    public Long getPauta_id() {
        return pauta_id;
    }

    public void setPauta_id(Long pauta_id) {
        this.pauta_id = pauta_id;
    }

    public PautaResultDomain() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtd_sim() {
        return qtd_sim;
    }

    public void setQtd_sim(Integer qtd_sim) {
        this.qtd_sim = qtd_sim;
    }

    public Integer getQtd_nao() {
        return qtd_nao;
    }

    public void setQtd_nao(Integer qtd_nao) {
        this.qtd_nao = qtd_nao;
    }

    private Integer qtd_sim;
    private Integer qtd_nao;
}
