package com.example.Alpha_Studio.DTO;

import jakarta.validation.constraints.NotNull;

public class AgendamentoDTO {

@NotNull(message = "Nome é Obrigatorio")
private String nome;

@NotNull(message = "Telefone é obrigatorio")
private String telefone;

@NotNull(message = "marca é obrigatorio")
private String marca;

@NotNull(message = "modelo é obrigatorio")
private String modelo;

@NotNull(message = "cor é obrigatorio")
private String cor;

@NotNull(message = "tipo do veiculo é obrigatorio")
private String tipoVeiculo;

@NotNull(message = "Servico é obrigatorio")
private String servico;

@NotNull(message = "data é obrigatorio")
private String data;

@NotNull(message = "hora é obrigatorio")
private String hora;

public AgendamentoDTO() {}

    public AgendamentoDTO(String nome, String telefone, String marca, String modelo, String cor, String tipoVeiculo, String servico, String data, String hora) {
        this.nome = nome;
        this.telefone = telefone;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.tipoVeiculo = tipoVeiculo;
        this.servico = servico;
        this.data = data;
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
