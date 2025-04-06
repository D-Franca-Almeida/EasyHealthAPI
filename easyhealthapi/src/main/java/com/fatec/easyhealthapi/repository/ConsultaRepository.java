package com.fatec.easyhealthapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.easyhealthapi.model.Consulta;


@Repository
public interface ConsultaRepository extends CrudRepository<Consulta, Integer> {
   Optional<List<Consulta>> findByPacienteId(Integer Id);
}

