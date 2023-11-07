/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.acme.validator;

import java.time.LocalDate;
import org.acme.Dog;

/**
 *
 * @author voulg
 */
public class DogValidator {
    private void validateMissingID(Dog dog) {
        if (dog.id != null) {
            throw new IllegalArgumentException("New dogs cannot have an ID.");
        }        
    }

    private void validateDogAttributes(Dog dog) {
        if (dog.name == null || dog.name.length() < 3 || dog.name.length() > 50)  {
            throw new IllegalArgumentException("Invalid name given.");            
        }
        
        if(dog.birthdate == null 
            || dog.birthdate.isAfter(LocalDate.now()) 
            || !dog.birthdate.isAfter(LocalDate.now().minusYears(30))) {
            throw new IllegalArgumentException("Invalid birthday given.");
        }
    }
    
    public void validateDogCreation(Dog dog) {
        validateMissingID(dog);
        validateDogAttributes(dog);
    }
    
    public void validateDogChange(Dog dog) {
        validateDogAttributes(dog);
    }
}
