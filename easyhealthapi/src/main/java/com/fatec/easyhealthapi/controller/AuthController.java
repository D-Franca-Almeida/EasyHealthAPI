package com.fatec.easyhealthapi.controller;

import com.fatec.easyhealthapi.service.AuthService;
// Remova o import do Lombok se ele não for mais usado em outras anotações
// import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
// @RequiredArgsConstructor // 1. REMOVA ESTA ANOTAÇÃO
public class AuthController {

    private final AuthService authService;

    // 2. ADICIONE O CONSTRUTOR MANUALMENTE
    // Este construtor faz exatamente o que o @RequiredArgsConstructor deveria fazer.
    // O Spring usará este construtor para injetar a dependência do AuthService.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup-paciente")
    public ResponseEntity<?> signupPaciente(@RequestBody Map<String, String> body) {
        try {
            authService.signupPaciente(body);
            return ResponseEntity.ok("Paciente cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    @PostMapping("/signup-profissional")
    public ResponseEntity<?> signupProfissional(@RequestBody Map<String, String> body) {
        try {
            authService.signupProfissional(body);
            return ResponseEntity.ok("Profissional cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar profissional: " + e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> credentials) {
        try {
            String jwtToken = authService.signin(
                    credentials.get("email"),
                    credentials.get("senha")
            );
            return ResponseEntity.ok(Map.of("token", jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }
}