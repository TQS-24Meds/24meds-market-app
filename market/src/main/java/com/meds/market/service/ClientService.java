package com.meds.market.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class ClientService {

    @Autowired private ClientRepository clientRepository;

    @Autowired private CoordinatesRepository coordRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    public Client registerClient(Client client) throws DuplicatedObjectException {
        
        Optional<Client> clientFoundByUsername = clientRepository.findByUsername(client.getUsername());
        Optional<Client> clientFoundByEmail = clientRepository.findByEmail(client.getEmail());

        if (client.getClient_location() == null) { throw new ResourceNotFoundException("Home location is required!"); }
        
        if (clientFoundByUsername.isPresent()) { throw new DuplicatedObjectException("The provided username is already being used!"); }

        if (clientFoundByEmail.isPresent()) { throw new DuplicatedObjectException("The provided email is already being used!"); }

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        coordRepository.save(client.getClient_location());

        return clientRepository.save(client);
    }
    
    public List<Purchase> getPurchases(String username) {
        Optional<Client> clientFoundByUsername = clientRepository.findByUsername(username);

        if (!clientFoundByUsername.isPresent()) { throw new ResourceNotFoundException("Client not found!"); }

        return clientFoundByUsername.get().getPurchases();
    }


    public Client getClientByUsername(String username){
        Optional<Client> clientFoundByUsername = clientRepository.findByUsername(username);

        if (!clientFoundByUsername.isPresent()) { throw new ResourceNotFoundException("Client not found!"); }
        return (Client) clientFoundByUsername.get();
    }

    public Client getClientByEmail(String email){
        Optional<Client> clientFoundByEmail = clientRepository.findByEmail(email);

        if (!clientFoundByEmail.isPresent()) { throw new ResourceNotFoundException("Client not found!"); }
        return (Client) clientFoundByEmail.get();
    }
    
    public Map<String, Object> convertClientToMap(Client client){
    
        Map<String, Object> mappedClient = new HashMap<>();

        mappedClient.put("name", client.getName());
        mappedClient.put("username", client.getUsername());
        mappedClient.put("email", client.getEmail());
        mappedClient.put("phone", client.getPhone());
        mappedClient.put("client_location", client.getClient_location());
        mappedClient.put("purchases", client.getPurchases());

        return mappedClient;
    }
    public List<Client> getAllClients() { return clientRepository.findAll(); }

}
