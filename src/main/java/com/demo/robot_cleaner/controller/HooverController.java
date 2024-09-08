package com.demo.robot_cleaner.controller;

import com.demo.robot_cleaner.dto.HooverRequestDto;
import com.demo.robot_cleaner.dto.HooverResponseDto;
import com.demo.robot_cleaner.mapper.HooverMapper;
import com.demo.robot_cleaner.service.HooverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hoover")
@Tag(name = "Hoover", description = "Hoover navigation API")
public class HooverController {

    @Autowired
    private HooverService hooverService;

    @Autowired
    private HooverMapper hooverMapper;

    @PostMapping("/navigate")
    @Operation(summary = "Navigate the hoover",
            description = "Provides navigation instructions for the hoover and returns its final position and number of patches cleaned")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = HooverResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<HooverResponseDto> navigateRoom(@Valid @RequestBody HooverRequestDto requestDto) {
        var request = hooverMapper.toModel(requestDto);
        var response = hooverService.processHooverMovement(request);
        var responseDto = hooverMapper.toDto(response);
        return ResponseEntity.ok(responseDto);
    }
}