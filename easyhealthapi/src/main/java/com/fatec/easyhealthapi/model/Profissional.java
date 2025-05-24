package com.fatec.easyhealthapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "profissionais")
@DiscriminatorValue("PROFISSIONAL")
@PrimaryKeyJoinColumn(name = "person_id")
public class Profissional extends Person {

	private String especialidade;
	private String identificacao;

    public Profissional() {}

    public Profissional(String especialidade, String identificacao) {
        this.especialidade = especialidade;
        this.identificacao = identificacao;
        
    }
    




	// Getters and Setters
	

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	
	
	
}
