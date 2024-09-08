package com.demo.robot_cleaner.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CoordinatesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCoordinates {
    String message() default "Invalid coordinates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}