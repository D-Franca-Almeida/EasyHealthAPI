package com.fatec.easyhealthapi.service;

import com.fatec.easyhealthapi.enums.TaskStatus;
import com.fatec.easyhealthapi.model.HabitTemplate;
import com.fatec.easyhealthapi.model.Objective;
import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.model.Task;
import com.fatec.easyhealthapi.repository.ObjectiveRepository; // IMPORT NOVO
import com.fatec.easyhealthapi.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException; // IMPORT NOVO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // INJEÇÃO DO NOVO REPOSITORY
    @Autowired
    private ObjectiveRepository objectiveRepository;

    // NOVO MÉTODO: Cria as tarefas para uma pessoa com base no objetivo escolhido
    //... dentro da classe TaskService

    public List<Task> assignHabitsByObjective(Person person, Integer objectiveId) {
        Objective chosenObjective = objectiveRepository.findById(objectiveId)
                .orElseThrow(() -> new EntityNotFoundException("Objective with ID " + objectiveId + " not found."));

        // Pega os modelos de hábito associados
        List<HabitTemplate> templates = chosenObjective.getHabitTemplates().stream().toList();

        // ================== ADICIONE ESTA LINHA PARA DEBUG ==================
        System.out.println("DEBUG: Encontrados " + templates.size() + " templates para o objetivo ID " + objectiveId);
        // ===================================================================

        List<Task> newTasks = templates.stream().map(template -> {
            Task task = new Task();
            task.setDescription(template.getDescription());
            task.setPerson(person);
            return task;
        }).collect(Collectors.toList());

        return taskRepository.saveAll(newTasks);
    }

    // MÉTODO ORIGINAL: pode ser usado internamente ou para atualizações
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    // MÉTODO ORIGINAL: pode ser mantido para fins administrativos
    public List<Task> findAll() {
        return taskRepository.findAll(); // Simplificado graças ao JpaRepository
    }

    // NOVO MÉTODO: Buscar tarefas por ID da pessoa
    public List<Task> findByPersonId(Integer personId) {
        return taskRepository.findByPersonId(personId);
    }

    // NOVO MÉTODO: Buscar uma única tarefa por ID
    public Optional<Task> findById(Integer id) {
        return taskRepository.findById(id);
    }

    // NOVO MÉTODO: Atualizar o status de uma tarefa
    public Task updateStatus(Integer taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    // NOVO MÉTODO: Deleção lógica (soft delete)
    public void deleteTask(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found."));
        task.setStatus(TaskStatus.DELETED);
        taskRepository.save(task);
    }
}