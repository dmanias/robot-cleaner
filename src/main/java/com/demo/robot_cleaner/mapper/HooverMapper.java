package com.demo.robot_cleaner.mapper;

import com.demo.robot_cleaner.dto.HooverRequestDto;
import com.demo.robot_cleaner.dto.HooverResponseDto;
import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import org.springframework.stereotype.Component;

@Component
public class HooverMapper {

    public HooverRequest toModel(HooverRequestDto dto) {
        return new HooverRequest(
                dto.getRoomSize(),
                dto.getCoords(),
                dto.getPatches(),
                dto.getInstructions()
        );
    }

    public HooverResponseDto toDto(HooverResponse model) {
        HooverResponseDto dto = new HooverResponseDto();
        dto.setCoords(model.getCoords());
        dto.setPatches(model.getPatches());
        return dto;
    }
}