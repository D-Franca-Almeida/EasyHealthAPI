package com.fatec.easyhealthapi.controller;

import com.fatec.easyhealthapi.model.Task;
import com.fatec.easyhealthapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task saved = taskService.save(task);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        List<Task> list = taskService.findAll();
        return ResponseEntity.ok(list);
    }
}