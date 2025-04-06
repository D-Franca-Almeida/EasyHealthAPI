package com.fatec.easyhealthapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "pacientes")
public class Paciente extends Person {

    public Paciente() {}

    public Paciente(Integer id) {
        super(); // ou super(id, ...), se quiser
    }

	
	//Getters and Setters
	

}
