package com.meds.market.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meds.market.repository.*;
import com.meds.market.model.*;
import com.meds.market.services.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    
    @Mock(lenient = true) private ClientRepository repository;

    @InjectMocks private ClientService service;

    Client john, alex, dan;

    @BeforeEach
    public void setUp() {
        john = new Client("John Doe", "johndoe", "mypassword", "john@doe.com", "john house", 911111111);
        john.setPerson_location( new Coordinates(-39.80711f, 151.71425f) );

        alex = new Client("Alex Brain", "alexbrain", "mypassword", "alex@brain.com", "alex brain", 922222222);
        dan = new Client("Dan Daniel", "dandainel", "mypassword", "dan@daniel.com", "dan daniel", 933333333);
        

        List<Client> allClients = Arrays.asList(john, alex, dan);

        Mockito.when(repository.findByUsername(john.getUsername())).thenReturn(Optional.of(john));
        Mockito.when(repository.findByUsername(alex.getUsername())).thenReturn(Optional.of(alex));
        Mockito.when(repository.findByUsername("wrong_username")).thenReturn(null);
        Mockito.when(repository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(repository.findAll()).thenReturn(allClients);
        Mockito.when(repository.findById(-99)).thenReturn(Optional.empty());

    }

}
