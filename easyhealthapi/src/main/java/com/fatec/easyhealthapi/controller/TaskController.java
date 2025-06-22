package com.fatec.easyhealthapi.controller;

import com.fatec.easyhealthapi.dto.UpdateTaskStatusDTO;
import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.model.Task;
// import com.fatec.easyhealthapi.service.PersonService; // 1. REMOVA ESTE IMPORT
import com.fatec.easyhealthapi.repository.PersonRepository; // 2. ADICIONE ESTE IMPORT
import com.fatec.easyhealthapi.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // @Autowired
    // private PersonService personService; // 3. REMOVA ESTA INJEÇÃO DE DEPENDÊNCIA

    @Autowired
    private PersonRepository personRepository; // 4. ADICIONE A INJEÇÃO DO REPOSITÓRIO CORRETO

    // Endpoint para ATRIBUIR os hábitos de um objetivo a uma pessoa
    @PostMapping("/person/{personId}/assign-objective")
    public ResponseEntity<List<Task>> assignObjectiveToPerson(
            @PathVariable Integer personId,
            @RequestParam Integer objectiveId) {

        // 5. ESTA É A MUDANÇA PRINCIPAL
        // Buscamos diretamente no PersonRepository. Graças à sua configuração de herança (@Inheritance),
        // o JPA sabe como encontrar um Paciente ou um Profissional a partir deste repositório pai.
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa (Paciente ou Profissional) não encontrada com o ID: " + personId));

        // Daqui para baixo, nada muda, pois o TaskService já espera um objeto do tipo Person.
        List<Task> createdTasks = taskService.assignHabitsByObjective(person, objectiveId);
        return ResponseEntity.ok(createdTasks);
    }

    // Endpoint para LISTAR as tarefas de uma pessoa
    @GetMapping("/person/{personId}")
    public ResponseEntity<List<Task>> getTasksByPerson(@PathVariable Integer personId) {
        List<Task> tasks = taskService.findByPersonId(personId);
        return ResponseEntity.ok(tasks);
    }

    // Endpoint para ATUALIZAR o status de uma tarefa
    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Integer taskId, @RequestBody UpdateTaskStatusDTO dto) {
        Task updatedTask = taskService.updateStatus(taskId, dto.getStatus());
        return ResponseEntity.ok(updatedTask);
    }

    // Endpoint para DELETAR (soft delete) uma tarefa
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}