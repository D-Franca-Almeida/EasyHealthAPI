package com.fatec.easyhealthapi.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.model.Token;
import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.repository.TokenRepository;
import com.fatec.easyhealthapi.repository.PersonRepository;

@Service
public class AuthService {
    private Integer TOKEN_TTL = 60 * 2; // Token válido por 2 minutos

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public void signup(String email, String password, String nome, Integer idade) throws Exception {
        Person person = new Person();
        person.setEmail(email);
        person.setSenha(password);
        person.setNome(nome);
        person.setIdade(idade);

        Optional<Person> personFound = personRepository.findByEmail(email);
        if (personFound.isPresent()) {
            throw new Exception("E-mail already exists");
        }

        personRepository.save(person);
    }

    public Token signin(String email, String senha) {
        Optional<Person> personFound = personRepository.findByEmail(email);
        if (personFound.isPresent() && personFound.get().getSenha().equals(senha)) {
            Token token = new Token();
            token.setPerson(personFound.get());
            token.setToken(UUID.randomUUID().toString());
            token.setExpirationTime(Instant.now()
                    .plusSeconds(TOKEN_TTL)
                    .toEpochMilli());
            token = tokenRepository.save(token);
            return token;
        }

        return null;
    }

    public Boolean validate(String token) {
        Optional<Token> found = tokenRepository.findByToken(token);
        return found.isPresent() && found.get().getExpirationTime() > Instant.now().toEpochMilli();
    }

    public void signout(String token) {
        Optional<Token> found = tokenRepository.findByToken(token);
        if (found.isPresent()) {
            tokenRepository.delete(found.get());
        }
    }
}