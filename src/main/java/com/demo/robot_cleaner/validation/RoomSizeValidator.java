package com.demo.robot_cleaner.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoomSizeValidator implements ConstraintValidator<ValidRoomSize, int[]> {
    @Override
    public boolean isValid(int[] roomSize, ConstraintValidatorContext context) {
        if (roomSize == null || roomSize.length != 2) {
            return false;
        }
        return roomSize[0] > 0 && roomSize[1] > 0;
    }
}