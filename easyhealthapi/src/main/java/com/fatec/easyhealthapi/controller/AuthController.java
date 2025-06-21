package com.fatec.easyhealthapi.controller;

import com.fatec.easyhealthapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // Injeta as dependências via construtor com Lombok
public class AuthController {

    private final AuthService authService; // A única dependência que o controller precisa

    // ✅ Cadastro de Paciente (Agora delegando para o serviço)
    @PostMapping("/signup-paciente")
    public ResponseEntity<?> signupPaciente(@RequestBody Map<String, String> body) {
        try {
            // A lógica de criar, validar e salvar foi movida para o AuthService
            authService.signupPaciente(body);
            return ResponseEntity.ok("Paciente cadastrado com sucesso.");
        } catch (Exception e) {
            // O serviço agora lança exceções que o controller captura
            return ResponseEntity.badRequest().body("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    // ✅ Cadastro de Profissional (Agora delegando para o serviço)
    @PostMapping("/signup-profissional")
    public ResponseEntity<?> signupProfissional(@RequestBody Map<String, String> body) {
        try {
            // A lógica de criar, validar e salvar foi movida para o AuthService
            authService.signupProfissional(body);
            return ResponseEntity.ok("Profissional cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar profissional: " + e.getMessage());
        }
    }

    // 🔐 Login (Agora retorna um token JWT)
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> credentials) {
        try {
            // O authService.signin agora retorna uma String (o token JWT)
            String jwtToken = authService.signin(
                    credentials.get("email"),
                    credentials.get("senha")
            );
            // É uma boa prática retornar o token dentro de um objeto JSON
            return ResponseEntity.ok(Map.of("token", jwtToken));
        } catch (Exception e) {
            // As exceções de autenticação do Spring Security serão capturadas aqui
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }

    // O endpoint /signout foi removido.
    // Com JWT, o logout é "stateless", ou seja, é responsabilidade do cliente
    // (o front-end) simplesmente apagar/descartar o token que ele tinha armazenado.
    // O servidor não precisa fazer nada.
}