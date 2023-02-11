package com.sudeepcv.controller;

import com.sudeepcv.dto.AuthRequest;
import com.sudeepcv.model.UserInfo;
import com.sudeepcv.repository.PeopleRepository;
import com.sudeepcv.repository.UserInfoRepository;
import com.sudeepcv.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/people")

public class PeopleController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/getAll")
    public ResponseEntity getPeople() {
        return ResponseEntity.ok(peopleRepository.findAll());
    }

    @GetMapping("/publicapi")
    public ResponseEntity publicApi() {
        return ResponseEntity.ok("this is public api no security");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity adminApi() {
        return ResponseEntity.ok("this is admin resource");
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity userApi() {
        return ResponseEntity.ok("this is user resource");
    }

    @PostMapping("/newuser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }


    @PostMapping("/authenticate")
    public ResponseEntity authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            HashMap<String,String> result=new LinkedHashMap<>();
            result.put("token",jwtService.generateToken(authRequest.getUsername()));
            return ResponseEntity.ok().body(result);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }



}
