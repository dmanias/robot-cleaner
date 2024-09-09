package com.demo.robot_cleaner.model;

import com.demo.robot_cleaner.validation.ValidRoomSize;
import com.demo.robot_cleaner.validation.ValidCoordinates;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

    public class HooverRequest {
        // Getters and Setters
        @ValidRoomSize(message = "Room size must be an array of 2 positive integers")
        private final int[] roomSize;

        @ValidCoordinates(message = "Initial coordinates must be an array of 2 non-negative integers")
        private final int[] coords;

        @NotNull(message = "Patches are required")
        private final List<@Size(min = 2, max = 2) int[]> patches;

        @NotNull(message = "Instructions are required")
        @Pattern(regexp = "^[NSEW]+$", message = "Instructions must only contain N, S, E, or W")
        private final String instructions;


    public HooverRequest(int[] roomSize, int[] coords, List<int[]> patches, String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

        public int[] getRoomSize() {
            return roomSize;
        }

        public int[] getCoords() {
            return coords;
        }

        public @NotNull(message = "Patches are required") List<@Size(min = 2, max = 2) int[]> getPatches() {
            return patches;
        }

        public @NotNull(message = "Instructions are required") @Pattern(regexp = "^[NSEW]+$", message = "Instructions must only contain N, S, E, or W") String getInstructions() {
            return instructions;
        }
    }
