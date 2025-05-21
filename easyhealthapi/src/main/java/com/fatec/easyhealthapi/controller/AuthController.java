package com.fatec.easyhealthapi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.easyhealthapi.enums.Genero;
import com.fatec.easyhealthapi.model.Token;
import com.fatec.easyhealthapi.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> user) {
        try {
            // Parse dos dados do usuário
            String email = user.get("email");
            String senha = user.get("senha");
            String nome = user.get("nome");
            String cpf = user.get("cpf");
            String telefone = user.get("telefone");
            String endereco = user.get("endereco");

            // Parse da data de nascimento
            LocalDate dataNascimento = LocalDate.parse(user.get("dataNascimento"),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Parse do gênero
            Genero genero = Genero.valueOf(user.get("genero").toUpperCase());

            // Chamada do serviço
            authService.signup(email, senha, nome, cpf, dataNascimento, genero, telefone, endereco);

            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Dados inválidos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> credentials) {
        try {
            Token token = authService.signin(
                    credentials.get("email"),
                    credentials.get("senha")
            );
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestHeader String token) {
        Boolean isValid = authService.validate(token);
        return (isValid) ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout(@RequestHeader String token) {
        authService.signout(token);
        return ResponseEntity.ok().build();
    }
}