package com.demo.robot_cleaner.dto;

import com.demo.robot_cleaner.validation.ValidRoomSize;
import com.demo.robot_cleaner.validation.ValidCoordinates;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HooverRequestDto {
    @Schema(description = "Room size as [width, height]", example = "[5, 5]")
    @ValidRoomSize
    private int[] roomSize;

    @Schema(description = "Initial hoover position as [x, y]", example = "[1, 2]")
    @ValidCoordinates
    private int[] coords;

    @Schema(description = "Positions of dirt patches as array of [x, y] coordinates", example = "[[1, 0], [2, 2], [2, 3]]")
    @NotNull(message = "Patches are required")
    private List<int[]> patches;

    @Schema(description = "Driving instructions as a string of characters N, S, E, W", example = "NNESEESWNWW")
    @NotNull(message = "Instructions are required")
    @Pattern(regexp = "^[NSEW]+$", message = "Instructions must only contain N, S, E, or W")
    private String instructions;
}