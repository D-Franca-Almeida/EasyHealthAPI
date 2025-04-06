package com.fatec.easyhealthapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.easyhealthapi.model.Profissional;

@Repository
public interface ProfissionalRepository extends CrudRepository<Profissional, Integer> {
    Optional<Profissional> findByEmail(String email);
}
