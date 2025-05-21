package com.fatec.easyhealthapi.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.model.Token;
import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.repository.TokenRepository;
import com.fatec.easyhealthapi.repository.PersonRepository;
import com.fatec.easyhealthapi.enums.Genero;

@Service
public class AuthService {
    private Integer TOKEN_TTL = 60 * 2; // Token válido por 2 minutos

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public void signup(String email, String senha, String nome, String cpf,
                       LocalDate dataNascimento, Genero genero, String telefone,
                       String endereco) throws Exception {

        // Verifica se email já existe
        if (personRepository.findByEmail(email).isPresent()) {
            throw new Exception("E-mail já cadastrado");
        }

        // Verifica se CPF já existe
        if (personRepository.findByCpf(cpf).isPresent()) {
            throw new Exception("CPF já cadastrado");
        }

        Person person = new Person();
        person.setEmail(email);
        person.setSenha(senha);
        person.setNome(nome);
        person.setCpf(cpf);
        person.setDataNascimento(dataNascimento);
        person.setGenero(genero);
        person.setTelefone(telefone);
        person.setEndereco(endereco);

        personRepository.save(person);
    }

    public Token signin(String email, String senha) throws Exception {
        Optional<Person> personOpt = personRepository.findByEmail(email);

        if (!personOpt.isPresent()) {
            throw new Exception("Usuário não encontrado");
        }

        Person person = personOpt.get();

        if (!person.getSenha().equals(senha)) {
            throw new Exception("Senha incorreta");
        }

        // Cria token de autenticação
        Token token = new Token();
        token.setPerson(person);
        token.setToken(UUID.randomUUID().toString());
        token.setExpirationTime(Instant.now()
                .plusSeconds(TOKEN_TTL)
                .toEpochMilli());

        return tokenRepository.save(token);
    }

    public Boolean validate(String token) {
        Optional<Token> found = tokenRepository.findByToken(token);
        return found.isPresent() &&
                found.get().getExpirationTime() > Instant.now().toEpochMilli();
    }

    public void signout(String token) {
        tokenRepository.findByToken(token).ifPresent(tokenRepository::delete);
    }
}