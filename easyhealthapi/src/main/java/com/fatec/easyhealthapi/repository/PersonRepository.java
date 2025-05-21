package com.fatec.easyhealthapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fatec.easyhealthapi.model.Person;

public interface PersonRepository   extends CrudRepository<Person,Integer> {
    // Métodos customizados podem ser adicionados aqui
	Optional<Person> findByEmail(String email);

    Optional<Object> findByCpf(String cpf);
}
