package com.fatec.easyhealthapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.fatec.easyhealthapi.model.Person;

public interface PersonRepository   extends CrudRepository<Person,Integer> {
    // Métodos customizados podem ser adicionados aqui
}
