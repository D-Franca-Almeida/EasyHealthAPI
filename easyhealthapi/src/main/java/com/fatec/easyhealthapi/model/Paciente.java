package com.fatec.easyhealthapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente extends Person {

    @Column(name = "ocupacao")
    private String ocupacao;

    @Column(name = "estado_civil")
    private String estadoCivil;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "nome_social")
    private String nomeSocial;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    public Paciente() {}

    public Paciente(Integer id) {
        super();
    }

    // Getters e Setters

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }
}
