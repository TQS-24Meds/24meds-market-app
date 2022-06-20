package com.meds.market.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meds.market.repository.*;
import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.services.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.internal.verification.VerificationModeFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    
    @Mock(lenient = true) private ClientRepository repository;

    @Mock(lenient = true) private CoordinatesRepository coordRepository;

    
    @InjectMocks private ClientService service;

    Client john, alex, dan;

    @BeforeEach
    void setUp() {
        john = new Client("John Doe", "johndoe", "john@doe.com", "johnpass", "john house", 911111111);
        john.setClient_location( new Coordinates(-39.80711, 151.71425) );

        alex = new Client("Alex Brain", "alexbrain", "alex@brain.com", "alexpass", "alex house", 922222222, new Coordinates(-39.80711, 151.71425));
        dan = new Client("Dan Daniel", "dandaniel",  "dan@daniel.com", "danpass",  "dan house", 933333333, new Coordinates(-39.80711, 151.71425));
        
        List<Client> allClients = Arrays.asList(john, alex, dan);

        Mockito.when(repository.save(any(Client.class))).thenReturn(john);
        Mockito.when(any(String.class)).thenReturn(john.getPassword());
        Mockito.when(coordRepository.save(any(Coordinates.class))).thenReturn(john.getClient_location());

        // set alex email as duplicate
        Mockito.when(repository.findByEmail(alex.getEmail())).thenReturn(Optional.of(alex));
        Mockito.when(repository.findByUsername(alex.getUsername())).thenReturn(Optional.ofNullable(null));
        
        // set dan username as duplicate
        Mockito.when(repository.findByEmail(dan.getEmail())).thenReturn(Optional.ofNullable(null));
        Mockito.when(repository.findByUsername(dan.getUsername())).thenReturn(Optional.of(dan));

        Mockito.when(repository.findByUsername("wrong_username")).thenReturn(null);
        Mockito.when(repository.findAll()).thenReturn(allClients);

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(john));
        Mockito.when(repository.findById(-1)).thenReturn(Optional.empty());

    }


    @Test
    void whenRegisterNewClient_thenReturnCreatedClient() throws DuplicatedObjectException {

        Client registeredClient = service.registerClient(john);
        assertThat( registeredClient ).isEqualTo( john );

		assertThat(registeredClient.getId()).isEqualTo(john.getId());
		Assertions.assertNotNull(registeredClient);
		Assertions.assertEquals(registeredClient.getUsername(), "johndoe");

        verifyFindByEmailIsCalledOnce(john.getEmail());
        verifyFindByUsernameIsCalledOnce(john.getUsername());
        
    }

    @Test
    void whenGetClientByValidUsername_thenReturnClient() {

        String validDanUsername = dan.getUsername();
        Mockito.when( repository.findByUsername(validDanUsername) ).thenReturn(Optional.of(dan));

        Client clientFound = service.getClientByUsername(validDanUsername);

		Assertions.assertEquals(clientFound, dan);

    }


    @Test
     void whenSearchInvalidUsername_thenClientShouldNotBeFound() {

        String username = john.getUsername();

        Mockito.when( repository.findByUsername(john.getUsername()) ).thenReturn(Optional.ofNullable(null));

        ResourceNotFoundException thrownException = assertThrows(ResourceNotFoundException.class, () -> service.getClientByUsername(username));

        assertThat(thrownException.getMessage()).isEqualTo("Client not found!");
    }

    @Test
    void whenRegisterClientWithDuplicateEmail_thenThrowException() {
        DuplicatedObjectException thrownException = assertThrows(DuplicatedObjectException.class, () -> service.registerClient(alex));

        assertThat(thrownException.getMessage()).isEqualTo("The provided email is already being used!");

        verifyFindByEmailIsCalledOnce(alex.getEmail());
        verifyFindByUsernameIsCalledOnce(alex.getUsername());
    }

    @Test
    void whenRegisterClientWithDuplicateUsername_thenThrowException() {
        DuplicatedObjectException thrownException = assertThrows(DuplicatedObjectException.class, () -> service.registerClient(dan));

        assertThat(thrownException.getMessage()).isEqualTo("The provided username is already being used!");

        verifyFindByEmailIsCalledOnce(dan.getEmail());
        verifyFindByUsernameIsCalledOnce(dan.getUsername());
    }

    @Test
    void whenRegisterClientWithNoHomeLocation_thenThrowException() {
        
        Client clientWithoutLocation = new Client("nohome name", "nohome_username", "nohome@email.com", "nohomepass", "address", 911111111);

        ResourceNotFoundException thrownException = assertThrows(ResourceNotFoundException.class, () -> service.registerClient(clientWithoutLocation));

        assertThat(thrownException.getMessage()).isEqualTo("Home location is required!");

        verifyFindByEmailIsCalledOnce(clientWithoutLocation.getEmail());
        verifyFindByUsernameIsCalledOnce(clientWithoutLocation.getUsername());
    }

    @Test
    void whenConvertClientToMap_thenReturnMappedClient() {
        Map<String, Object> mappedClient = service.convertClientToMap(john);

        assertThat(mappedClient.get("name")).isEqualTo(john.getName());
        assertThat(mappedClient.get("username")).isEqualTo(john.getUsername());
        assertThat(mappedClient.get("email")).isEqualTo(john.getEmail());
        assertThat(mappedClient.get("phone")).isEqualTo(john.getPhone());
        assertThat(mappedClient.get("client_location")).isEqualTo(john.getClient_location());
        assertThat(mappedClient.get("purchases")).isEqualTo(john.getPurchases());
    }

    @Test
    void whenGetValidClientOrder_thenReturnOrders() {
        
        String validDanUsername = dan.getUsername();

        Mockito.when( repository.findByUsername(validDanUsername) ).thenReturn(Optional.of(dan));

        List<Purchase> purchases = service.getPurchases(validDanUsername);

        assertThat(purchases).isEqualTo(dan.getPurchases());
    }

    @Test
    void given3Clients_whengetAll_thenReturn3Records() {

       List<Client> allClients = service.getAllClients();
       verifyFindAllClientsIsCalledOnce();
       assertThat(allClients).hasSize(3).extracting(Client::getUsername).contains(alex.getUsername(), john.getUsername(), dan.getUsername());
   }

    void verifyFindByUsernameIsCalledOnce(String name) { Mockito.verify(repository, VerificationModeFactory.times(1)).findByUsername(name); }

    void verifyFindByEmailIsCalledOnce(String name) { Mockito.verify(repository, VerificationModeFactory.times(1)).findByEmail(name); }

    void verifyFindAllClientsIsCalledOnce() { Mockito.verify(repository, VerificationModeFactory.times(1)).findAll(); }

}