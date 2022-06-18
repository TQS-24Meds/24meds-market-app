package com.meds.market.controller;


import java.util.ArrayList;
import java.util.List;

import com.meds.market.services.*;
import com.meds.market.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ClientController {
    
    private PasswordEncoder passwordEncoder;
    private ClientService service;

    @Autowired
    public ClientController(PasswordEncoder passwordEncoder, ClientService service) { 
        this.passwordEncoder = passwordEncoder;    
        this.service = service; 
    }

    @GetMapping("/{email}/details")
    public Client getClientDetails(@PathVariable(value = "email") String email) { return service.getClientByEmail (email); }

}
