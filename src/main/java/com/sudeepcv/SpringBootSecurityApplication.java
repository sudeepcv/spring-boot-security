package com.sudeepcv;

import com.sudeepcv.model.People;
import com.sudeepcv.repository.PeopleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityApplication {
	@Autowired
	PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}
	@PostConstruct
	public void addData(){
		peopleRepository.save(People.builder().name("suddp").build());
		peopleRepository.save(People.builder().name("anju").build());
	}

}
