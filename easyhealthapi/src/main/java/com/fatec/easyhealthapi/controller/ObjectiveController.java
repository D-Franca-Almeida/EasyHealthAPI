package com.fatec.easyhealthapi.controller;

import com.fatec.easyhealthapi.model.Objective;
import com.fatec.easyhealthapi.repository.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    // Endpoint para o front-end buscar a lista de objetivos disponíveis
    @GetMapping
    public ResponseEntity<List<Objective>> listAll() {
        return ResponseEntity.ok(objectiveRepository.findAll());
    }
}