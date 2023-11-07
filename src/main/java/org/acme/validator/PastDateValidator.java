/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.acme.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 *
 * @author ctoska
 */
public class PastDateValidator implements ConstraintValidator<PastDate, LocalDate> {
    private int maxYearsPast;

    @Override
    public void initialize(PastDate constraintAnnotation) {
        this.maxYearsPast = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null && value.isAfter(LocalDate.now().minusYears(maxYearsPast));
    }
}