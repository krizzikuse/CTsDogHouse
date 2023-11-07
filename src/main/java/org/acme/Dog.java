/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.acme.validator.PastDate;

/**
 *
 * @author ctoska
 */
@Entity
public class Dog extends PanacheEntity {
    @NotBlank @Size(min = 3, max = 50)
    public String name;

    @PastDate(30)
    public LocalDate birthdate;

    @Enumerated(EnumType.STRING) @NotNull
    public Gender gender;

    public enum Gender {
        MALE, FEMALE
    }
}
