package com.fatec.easyhealthapi.model;

import com.fatec.easyhealthapi.enums.EasyHealthStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Consulta consulta;
    @Enumerated(EnumType.STRING) // Adicione esta anotação
    private EasyHealthStatus status = EasyHealthStatus.PENDING;
    private Double valor;
    
    //Construtores 
    public Pagamento() {
    	
    }

	public Pagamento(Long id, Consulta consulta, EasyHealthStatus status, Double valor) {
		super();
		this.id = id;
		this.consulta = consulta;
		this.status = status;
		this.valor = valor;
	}
    //Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public EasyHealthStatus getStatus() {
		return status;
	}

	public void setStatus(EasyHealthStatus status) {
		this.status = status;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	
	
    
    
    
    
}
