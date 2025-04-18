package com.fatec.easyhealthapi.model;

import com.fatec.easyhealthapi.enums.EasyHealthStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED) // ou SINGLE_TABLE, se preferir
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Integer idade;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private EasyHealthStatus status = EasyHealthStatus.PENDING;
	

    //Constructors
    public Person() {
    	
    }
    
    
    public Person(Integer id, String nome, Integer idade, EasyHealthStatus status) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.status = status;
	}


	// Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public EasyHealthStatus getStatus() {
		return status;
	}


	public void setStatus(EasyHealthStatus status) {
		this.status = status;
	}
    
    
    
}
