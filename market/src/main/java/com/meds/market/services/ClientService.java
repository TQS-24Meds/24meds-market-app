package com.meds.market.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class ClientService {

    @Autowired private ClientRepository clientRepository;

    @Autowired private CoordinatesRepository coordRepository;


    Client registerClient(Client client) throws DuplicatedObjectException {
    
        Optional<Person> clientFoundByUsername = clientRepository.findByUsername(client.getUsername());
        Optional<Person> clientFoundByEmail = clientRepository.findByEmail(client.getEmail());

        if (client.getPerson_location() == null) { throw new ResourceNotFoundException("Home location is required."); }
        
        if (clientFoundByUsername.isPresent()) { throw new DuplicatedObjectException("The provided username is already being used."); }

        if (clientFoundByEmail.isPresent()) { throw new DuplicatedObjectException("The provided email is already being used."); }

        client.setPassword(client.getPassword());
        Client clientToRegister = client;

        coordRepository.save(clientToRegister.getPerson_location());

        return clientRepository.save(clientToRegister);
    }
    Client getClientByUsername(String username){
    
        Optional<Person> clientFoundByUsername = clientRepository.findByUsername(username);

        if (!clientFoundByUsername.isPresent()) { throw new ResourceNotFoundException("Client not found."); }
        return (Client) clientFoundByUsername.get();
    }

    Map<String, Object> convertClientToMap(Client client){
    
        Map<String, Object> mappedClient = new HashMap<>();

        mappedClient.put("name", client.getName());
        mappedClient.put("username", client.getUsername());
        mappedClient.put("email", client.getEmail());
        mappedClient.put("phone", client.getPhone());
        mappedClient.put("person_location", client.getPerson_location());
        mappedClient.put("purchases", client.getPurchases());

        return mappedClient;
    }

}
