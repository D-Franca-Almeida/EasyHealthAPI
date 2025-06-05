package com.fatec.easyhealthapi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.easyhealthapi.enums.Genero;
import com.fatec.easyhealthapi.model.*;
import com.fatec.easyhealthapi.repository.PacienteRepository;
import com.fatec.easyhealthapi.repository.ProfissionalRepository;
import com.fatec.easyhealthapi.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @PostMapping("/signup-paciente")
    public ResponseEntity<?> signupPaciente(@RequestBody Map<String, String> body) {
        try {
            Paciente paciente = new Paciente();
            paciente.setNome(body.get("nome"));
            paciente.setEmail(body.get("email"));
            paciente.setSenha(body.get("senha")); // Ideal: criptografar
            paciente.setCpf(body.get("cpf"));
            paciente.setTelefone(body.get("telefone"));
            paciente.setEndereco(body.get("endereco"));
            paciente.setDataNascimento(LocalDate.parse(body.get("dataNascimento"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            paciente.setGenero(Genero.valueOf(body.get("genero").toUpperCase()));

            paciente.setAltura(Double.parseDouble(body.get("altura"))); // Altura em metros
            paciente.setPeso(Double.parseDouble(body.get("peso")));     // Peso em kg
            paciente.setEstadoCivil(body.get("estadocivil"));
            paciente.setNacionalidade(body.get("nacionalidade"));
            paciente.setNomeSocial(body.get("nomesocial"));
            paciente.setOcupacao(body.get("ocupacao"));

            pacienteRepository.save(paciente);

            return ResponseEntity.ok("Paciente cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }



    @PostMapping("/signup-profissional")
    public ResponseEntity<?> signupProfissional(@RequestBody Map<String, String> body) {
        try {
            Profissional profissional = new Profissional();
            profissional.setNome(body.get("nome"));
            profissional.setEmail(body.get("email"));
            profissional.setSenha(body.get("senha"));
            profissional.setCpf(body.get("cpf"));
            profissional.setTelefone(body.get("telefone"));
            profissional.setEndereco(body.get("endereco"));
            profissional.setDataNascimento(LocalDate.parse(body.get("dataNascimento"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            profissional.setGenero(Genero.valueOf(body.get("genero").toUpperCase()));

            // Campos específicos
            profissional.setEspecialidade(body.get("especialidade"));
            profissional.setIdentificacao(body.get("identificacao"));

            profissionalRepository.save(profissional);

            return ResponseEntity.ok("Profissional cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar profissional: " + e.getMessage());
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
