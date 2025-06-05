package com.fatec.easyhealthapi.controller;

import com.fatec.easyhealthapi.model.Consulta;
import com.fatec.easyhealthapi.service.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar")
    public ResponseEntity<Consulta> agendar(@RequestBody Map<String, String> dados) {
        Integer pacienteId = Integer.parseInt(dados.get("pacienteId"));
        Integer profissionalId = Integer.parseInt(dados.get("profissionalId"));
        String dataHoraStr = dados.get("dataHora");
        String motivo = dados.get("motivo");

        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        Consulta consulta = consultaService.agendar(pacienteId, profissionalId, dataHora, motivo);

        return ResponseEntity.ok(consulta);
    }
}
