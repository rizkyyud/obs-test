package com.obs.obs_test.usecase;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Component
public class ValidatorUseCase {

    @Autowired
    private Validator validator;

    public void execute(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);

        if (constraintViolations.size() != 0) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
