package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import com.demo.robot_cleaner.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HooverService {

    private final MovementService movementService;
    private final CleaningService cleaningService;

    @Autowired
    public HooverService(MovementService movementService, CleaningService cleaningService) {
        this.movementService = movementService;
        this.cleaningService = cleaningService;
    }

    public HooverResponse processHooverMovement(HooverRequest request) {
        validateRequest(request);

        Room room = new Room(request.getRoomSize(), request.getPatches());
        Hoover hoover = new Hoover(request.getCoords());

        int dirtCleaned = 0;

        for (char instruction : request.getInstructions().toCharArray()) {
            movementService.moveHoover(instruction, hoover, room);

            if (cleaningService.cleanDirt(room, hoover)) {
                dirtCleaned++;
            }
        }

        return new HooverResponse(hoover.getCoords(), dirtCleaned);
    }

    private void validateRequest(HooverRequest request) {
        if (request.getRoomSize()[0] <= 0 || request.getRoomSize()[1] <= 0) {
            throw new IllegalArgumentException("Room size must be positive");
        }

        if (request.getCoords()[0] < 0 || request.getCoords()[1] < 0 ||
                request.getCoords()[0] >= request.getRoomSize()[0] ||
                request.getCoords()[1] >= request.getRoomSize()[1]) {
            throw new IllegalArgumentException("Initial hoover position is outside the room");
        }

        for (int[] patch : request.getPatches()) {
            if (patch[0] < 0 || patch[1] < 0 ||
                    patch[0] >= request.getRoomSize()[0] ||
                    patch[1] >= request.getRoomSize()[1]) {
                throw new IllegalArgumentException("Dirt patch position is outside the room");
            }
        }
    }
}