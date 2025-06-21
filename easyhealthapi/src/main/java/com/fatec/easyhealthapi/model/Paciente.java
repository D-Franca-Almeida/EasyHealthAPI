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

    // Todos os getters e setters manuais foram removidos!
    // O Lombok cuidará de criar getAltura(), setAltura(), getPeso(), setPeso(), etc.
}