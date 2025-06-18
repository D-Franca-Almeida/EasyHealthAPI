package com.fatec.easyhealthapi.repository;

import com.fatec.easyhealthapi.model.HabitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitTemplateRepository extends JpaRepository<HabitTemplate, Integer> {
}