package com.fatec.easyhealthapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Profissional profissional;
    private LocalDateTime dataHora;
    private String status;
    
    // Construtores
    
    public Consulta() {
    	
    }

	public Consulta(Integer id, Paciente paciente, Profissional profissional, LocalDateTime dataHora, String status) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.profissional = profissional;
		this.dataHora = dataHora;
		this.status = status;
	}
	
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    

	
    
    
    
    
}

