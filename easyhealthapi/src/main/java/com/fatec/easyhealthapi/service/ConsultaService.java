package com.fatec.easyhealthapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.model.Consulta;
import com.fatec.easyhealthapi.repository.ConsultaRepository;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta agendarConsulta(Consulta consulta) {
        consulta.setStatus("AGENDADA");
        return consultaRepository.save(consulta);
    }
}
