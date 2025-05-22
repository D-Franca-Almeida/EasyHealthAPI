package com.fatec.easyhealthapi.service;

import com.fatec.easyhealthapi.model.Task;
import com.fatec.easyhealthapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        List<Task> list = new ArrayList<Task>();
        taskRepository.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

}