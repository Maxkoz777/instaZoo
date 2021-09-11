package com.example.instazoo.validations;

import com.example.instazoo.annotations.PasswordMatches;
import com.example.instazoo.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest request = (SignupRequest) o;
        return request.getPassword().equals(request.getConfirmPassword());
    }
}
