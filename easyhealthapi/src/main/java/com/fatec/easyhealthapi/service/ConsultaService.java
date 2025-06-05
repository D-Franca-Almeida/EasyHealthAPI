package com.fatec.easyhealthapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.model.Consulta;
import com.fatec.easyhealthapi.repository.ConsultaRepository;
import com.fatec.easyhealthapi.repository.PacienteRepository;
import com.fatec.easyhealthapi.repository.ProfissionalRepository;

import java.time.LocalDateTime;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Consulta agendar(Integer pacienteId, Integer profissionalId, LocalDateTime dataHora, String motivo) {

        var paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        var profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        var consulta = new Consulta();
        consulta.setDataHora(dataHora);
        consulta.setMotivo(motivo);
        consulta.setPaciente(paciente);
        consulta.setProfissional(profissional);

        return consultaRepository.save(consulta);
    }
}
