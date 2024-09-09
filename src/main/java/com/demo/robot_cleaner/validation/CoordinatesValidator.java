package com.demo.robot_cleaner.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoordinatesValidator implements ConstraintValidator<ValidCoordinates, int[]> {
    @Override
    public boolean isValid(int[] coords, ConstraintValidatorContext context) {
        if (coords == null || coords.length != 2) {
            return false;
        }
        return coords[0] >= 0 && coords[1] >= 0;
    }
}