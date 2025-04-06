package com.fatec.easyhealthapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.easyhealthapi.model.Profissional;
import com.fatec.easyhealthapi.service.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

	@Autowired
    private ProfissionalService profissionalService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Profissional> cadastrar(@RequestBody Profissional profissional) {
        return ResponseEntity.ok(profissionalService.cadastrar(profissional));
    }
}
