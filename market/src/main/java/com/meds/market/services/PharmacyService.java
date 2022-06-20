package com.meds.market.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meds.market.exception.*;
import com.meds.market.model.*;
import com.meds.market.repository.*;

@Service
public class PharmacyService {
    
    @Autowired private PharmacyRepository repository;
    
    public Pharmacy registerPharmacy(Pharmacy pharmacy) {

        Pharmacy registeredPharmacy;

        try { registeredPharmacy = repository.save(pharmacy); } 
        catch (Exception e) { throw new ObjectErrorException("Failed to register pharmacy!"); }

        return registeredPharmacy;
    }

    public List<Pharmacy> getAllPharmacys(){ return repository.findAll(); }
    
    public Pharmacy getPharmacy(int id){
        Optional<Pharmacy> pharmarcyFound = repository.findById(id);

        if (!pharmarcyFound.isPresent())
            throw new ResourceNotFoundException("Pharmacy not found!");

        return pharmarcyFound.get();
    }

}
