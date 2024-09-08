package com.demo.robot_cleaner.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoomSizeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoomSize {
    String message() default "Invalid room size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}