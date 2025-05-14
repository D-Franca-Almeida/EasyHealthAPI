package com.fatec.easyhealthapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.easyhealthapi.model.Token;
import com.fatec.easyhealthapi.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody Map<String, String> user) {
        String email = user.get("email");
        String senha = user.get("senha");
        String nome = user.get("nome");
        Integer idade = Integer.parseInt(user.get("idade"));
        try {
            authService.signup(email, senha, nome, idade);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Token> signin(
            @RequestBody Map<String, String> user) {
        Token token = authService.signin(
                user.get("email"),
                user.get("senha")
        );
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(token);
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