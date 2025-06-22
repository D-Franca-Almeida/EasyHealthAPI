package com.fatec.easyhealthapi.repository;

import java.util.Optional;

// import org.springframework.data.repository.CrudRepository; // Remover esta linha
import org.springframework.data.jpa.repository.JpaRepository; // Adicionar esta linha

import com.fatec.easyhealthapi.model.Person;

// Apenas troque CrudRepository por JpaRepository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Optional<Person> findByEmail(String email);

    // O método findByCpf retornando Optional<Object> pode causar problemas.
    // O ideal é que ele retorne Optional<Person> também.
    Optional<Person> findByCpf(String cpf);
}