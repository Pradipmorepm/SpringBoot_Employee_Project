package com.SampleProjSpring.Demo.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {
    @Override
    public boolean isValid(String inputString, ConstraintValidatorContext constraintValidatorContext) {
        if(inputString == null) return false;
        List<String> list = List.of("ADMIN", "USER");
        return list.contains(inputString);
    }
}
