package com.fatec.easyhealthapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.easyhealthapi.model.Person;
import com.fatec.easyhealthapi.repository.PersonRepository;
@Service
public class EasyHealthApiService {
	    @Autowired
		private PersonRepository personRepository;

		public Person save(Person person) {
			// TODO Auto-generated method stub
			
			return personRepository.save(person);
		}
		public List<Person> findAll(){
			
			List<Person> list = new ArrayList<Person>();
			personRepository.findAll().iterator().forEachRemaining(list::add);
			
			return list;
			
		}
		
		public Person findById(Integer id) {
		    return personRepository.findById(id).orElse(null);
		}

		

}
