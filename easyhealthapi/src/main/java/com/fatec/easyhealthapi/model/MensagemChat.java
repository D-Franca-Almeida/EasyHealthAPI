package com.fatec.easyhealthapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensagens_chat")
public class MensagemChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Profissional profissional;
    private String mensagem;
    private LocalDateTime dataHora;
    
    
    // Construtores
    public MensagemChat() {
    	
    }


	public MensagemChat(Integer id, Paciente paciente, Profissional profissional, String mensagem,
			LocalDateTime dataHora) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.profissional = profissional;
		this.mensagem = mensagem;
		this.dataHora = dataHora;
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


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public LocalDateTime getDataHora() {
		return dataHora;
	}


	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	
	
    
}
