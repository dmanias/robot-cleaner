package com.demo.robot_cleaner.model;

import com.demo.robot_cleaner.validation.ValidRoomSize;
import com.demo.robot_cleaner.validation.ValidCoordinates;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

    @Getter
    public class HooverRequest {
        // Getters and Setters
        @ValidRoomSize(message = "Room size must be an array of 2 positive integers")
        private int[] roomSize;

        @ValidCoordinates(message = "Initial coordinates must be an array of 2 non-negative integers")
        private int[] coords;

        @NotNull(message = "Patches are required")
        private List<@Size(min = 2, max = 2) int[]> patches;

        @NotNull(message = "Instructions are required")
        @Pattern(regexp = "^[NSEW]+$", message = "Instructions must only contain N, S, E, or W")
        private String instructions;


    public HooverRequest(int[] roomSize, int[] coords, List<int[]> patches, String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

    }
