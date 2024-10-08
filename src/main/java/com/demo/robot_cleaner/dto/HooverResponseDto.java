package com.demo.robot_cleaner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HooverResponseDto {
    @Schema(description = "Final hoover position as [x, y]", example = "[1, 3]")
    private int[] coords;

    @Schema(description = "Number of dirt patches cleaned", example = "1")
    private int patches;
}