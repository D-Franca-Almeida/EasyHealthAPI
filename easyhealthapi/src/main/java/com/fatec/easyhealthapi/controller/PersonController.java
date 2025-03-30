package com.fatec.easyhealthapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.service.EasyHealthApiService;

@RestController
@RequestMapping("/person")
public class PersonController {

   
    @Autowired
    private EasyHealthApiService easyHealthApiService;

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return easyHealthApiService.save(person);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Integer id) {
        return (Person) easyHealthApiService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
    	List<Person> list = easyHealthApiService.findAll();
    	
        return ResponseEntity.ok(list);
    }
   

}
