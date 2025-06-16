package com.example.Alpha_Studio.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PrecoServico")
public class PrecoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipoVeiculo;
    private String servico;
    private BigDecimal valor;


    public PrecoServico() {
    }

    public PrecoServico(Long id, String tipoVeiculo, String servico, BigDecimal valor) {
        this.id = id;
        this.tipoVeiculo = tipoVeiculo;
        this.servico = servico;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
