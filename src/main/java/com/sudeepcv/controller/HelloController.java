package com.sudeepcv.controller;

import com.sudeepcv.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloController {
    @Autowired
    PeopleRepository peopleRepository;
    @GetMapping("/")
    public ResponseEntity getPeople(){
        return ResponseEntity.ok(peopleRepository.findAll());
    }
}
