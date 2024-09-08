package com.demo.robot_cleaner.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoordinatesValidator implements ConstraintValidator<ValidCoordinates, int[]> {
    @Override
    public boolean isValid(int[] coords, ConstraintValidatorContext context) {
        return coords != null && coords.length == 2;

        // We need access to the room size, which isn't available here.
        // We'll perform this check in the service layer.
    }
}