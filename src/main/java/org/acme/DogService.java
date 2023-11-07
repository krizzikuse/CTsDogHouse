/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.acme.validator.DogValidator;


/**
 *
 * @author ctoska
 */
@ApplicationScoped
public class DogService {
    private final DogRepository dogRepository;

    private final DogValidator validator = new DogValidator();
    
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public synchronized Dog createDog(Dog dog) {     
        validator.validateDogCreation(dog);
        
        if (dogRepository.count() >= 10) {
            throw new IllegalArgumentException("DogHouse is full. Cannot add more dogs.");
        }
                  
        return saveDog(dog);
    }

    @Transactional
    public Dog saveDog(Dog dog) {
        dog.persist();
        return dog;
    }

    public Dog getDogById(Long id) {
        Dog dog = dogRepository.findById(id);
        if (dog == null) {
            throw new NotFoundException("Dog not found with ID: " + id);
        }
        return dog;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.listAll();
    }

    @Transactional
    public Dog updateDog(Long id, Dog updatedDog) {
        Dog dog = getDogById(id);
        dog.name = updatedDog.name;
        dog.birthdate = updatedDog.birthdate;
        dog.gender = updatedDog.gender;
        validator.validateDogChange(dog);
        return dog;
    }

    @Transactional
    public void deleteDog(Long id) {
        Dog dog = getDogById(id);
        dog.delete();
    }

    public List<Dog> findDogsByNameSortedByBirthdate(String name) {
        return dogRepository.findByNameSortedByBirthdate(name);
    }
}