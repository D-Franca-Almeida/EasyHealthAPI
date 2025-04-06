package com.fatec.easyhealthapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.model.Profissional;
import com.fatec.easyhealthapi.repository.ProfissionalRepository;
@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional cadastrar(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public Optional<Profissional> buscarPorEmail(String email) {
        return profissionalRepository.findByEmail(email);
    }
}
