package com.example.Alpha_Studio.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é Obrigatorio")
    private String nome;

    @NotNull(message = "telefone é obrigatorio")
    private String telefone;

    @NotNull(message = "Marca é obrigatorio")
    private String marca;

    @NotNull(message = "modelo é obrigatorio")
    private String modelo;

    @NotNull(message = "Cor é obrigatorio")
    private String cor;

    @NotNull(message = "tipo do veiculo é obrigatorio")
    private String tipoVeiculo;

    @NotNull(message = "o tipo de serviço é obrigatorio")
    private String servico;

    @NotNull(message = "data é obrigatorio")
    private LocalDate data;

    @NotNull(message = "horario é obrigatorio")
    private LocalTime hora;

    @NotNull(message = "Valor é obrigatorio")
    private BigDecimal valor;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dataHoraCadastrado;

public Agendamento() {}

public Agendamento(Long id, String nome, String telefone, String marca, String modelo, String cor, String tipoVeiculo, String servico, LocalDate data, LocalTime hora, BigDecimal valor, LocalDateTime dataHoraCadastrado) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.marca = marca;
    this.modelo = modelo;
    this.cor = cor;
    this.tipoVeiculo = tipoVeiculo;
    this.servico = servico;
    this.data = data;
    this.hora = hora;
    this.valor = valor;
    this.dataHoraCadastrado = dataHoraCadastrado;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHoraCadastrado() {
        return dataHoraCadastrado;
    }

    public void setDataHoraCadastrado(LocalDateTime dataHoraCadastrado) {
        this.dataHoraCadastrado = dataHoraCadastrado;
    }
}
