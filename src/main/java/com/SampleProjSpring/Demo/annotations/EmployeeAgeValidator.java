package com.SampleProjSpring.Demo.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator<EmployeeAgeValidation, Integer> {
    @Override
    public boolean isValid(Integer inputAge, ConstraintValidatorContext constraintValidatorContext) {

        for(int i=2;i<inputAge;i++){
            if(inputAge % i == 0){
                return false;
            }
        }
        return true;
    }


}

