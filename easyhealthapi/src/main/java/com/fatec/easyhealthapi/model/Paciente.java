package com.fatec.easyhealthapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "pacientes")
@Data // Adiciona getters, setters, etc. para todos os campos
@EqualsAndHashCode(callSuper = true) // Importante para herança: faz com que o equals/hashCode considere os campos da classe mãe (Person)
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

<<<<<<< HEAD
    // Getters e Setters manuais
    public String getOcupacao() { return ocupacao; }
    public void setOcupacao(String ocupacao) { this.ocupacao = ocupacao; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }
    public String getNomeSocial() { return nomeSocial; }
    public void setNomeSocial(String nomeSocial) { this.nomeSocial = nomeSocial; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }
}
=======
    // Todos os getters e setters manuais foram removidos!
    // O Lombok cuidará de criar getAltura(), setAltura(), getPeso(), setPeso(), etc.
}
>>>>>>> parent of f2f66c5 (refatorando código e adicionando jwt)
