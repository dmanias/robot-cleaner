package com.demo.robot_cleaner.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class HooverRequestDto {
    @Schema(description = "Room size as [width, height]", example = "[5, 5]")
    @NotNull(message = "Room size is required")
    @Size(min = 2, max = 2, message = "Room size must be an array of 2 integers")
    @Pattern(regexp = "^[0-9]+,[0-9]+$")
    private int[] roomSize;

    @Schema(description = "Initial hoover position as [x, y]", example = "[1, 2]")
    @NotNull(message = "Initial coordinates are required")
    @Size(min = 2, max = 2, message = "Initial coordinates must be an array of 2 integers")
    private int[] coords;

    @Schema(description = "Positions of dirt patches as array of [x, y] coordinates", example = "[[1, 0], [2, 2], [2, 3]]")
    @NotNull(message = "Patches are required")
    private List<int[]> patches;

    @Schema(description = "Driving instructions as a string of characters N, S, E, W", example = "NNESEESWNWW")
    @NotNull(message = "Instructions are required")
    @Pattern(regexp = "^[NSEW]+$", message = "Instructions must only contain N, S, E, or W")
    private String instructions;

    @NotNull(message = "Room size is required")
    @Size(min = 2, max = 2, message = "Room size must be an array of 2 integers")
    public int[] getRoomSize() {
        return roomSize;
    }

    @NotNull(message = "Initial coordinates are required")
    @Size(min = 2, max = 2, message = "Initial coordinates must be an array of 2 integers")
    public int[] getCoords() {
        return coords;
    }

    public @NotNull(message = "Patches are required") List<int[]> getPatches() {
        return patches;
    }

    public @NotNull(message = "Instructions are required") @Pattern(regexp = "^[NSEW]+$", message = "Instructions must only contain N, S, E, or W") String getInstructions() {
        return instructions;
    }

    // Getters and setters
}