package com.fatec.easyhealthapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "profissionais")
@PrimaryKeyJoinColumn(name = "person_id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Profissional extends Person {

	private String especialidade;
	private String identificacao;

	// Construtores, getters e setters manuais removidos.
}