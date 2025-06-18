package com.fatec.easyhealthapi.repository;

import com.fatec.easyhealthapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository; // RECOMENDAÇÃO: Usar JpaRepository
import org.springframework.stereotype.Repository;

import java.util.List; // Importar List

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByPersonId(Integer personId);
}