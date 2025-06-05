package com.fatec.easyhealthapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fatec.easyhealthapi.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
