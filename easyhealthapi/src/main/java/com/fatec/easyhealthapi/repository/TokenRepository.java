package com.fatec.easyhealthapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatec.easyhealthapi.model.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer> {

    Optional<Token> findByToken(String token);
}