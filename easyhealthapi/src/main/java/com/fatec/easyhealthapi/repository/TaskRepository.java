package com.fatec.easyhealthapi.repository;

import com.fatec.easyhealthapi.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

}
