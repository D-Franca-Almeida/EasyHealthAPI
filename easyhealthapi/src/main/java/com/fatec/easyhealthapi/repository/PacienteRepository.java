package com.fatec.easyhealthapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.easyhealthapi.model.Paciente;

@Repository
public interface PacienteRepository extends CrudRepository<Paciente, Integer> {
    Optional<Paciente> findByEmail(String email);
}
