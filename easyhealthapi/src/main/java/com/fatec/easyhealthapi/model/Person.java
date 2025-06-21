package com.fatec.easyhealthapi.model;

import com.fatec.easyhealthapi.enums.EasyHealthStatus;
import com.fatec.easyhealthapi.enums.Genero;
import jakarta.persistence.*;
import lombok.Data; // Importe o Lombok

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@Data // Esta única anotação substitui @Getter, @Setter, @ToString, @EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private EasyHealthStatus status = EasyHealthStatus.PENDING;

    private String endereco;

    // Os construtores e todos os getters/setters podem ser removidos.
    // O Lombok os gerará em tempo de compilação.
    // Apenas o método com lógica customizada (getIdade) permanece.

    public int getIdade(){
        if (this.dataNascimento == null) return 0;
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}