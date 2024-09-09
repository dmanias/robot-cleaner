package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.exception.OutOfBoundsException;
import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import com.demo.robot_cleaner.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HooverService {

    private static final Logger logger = LoggerFactory.getLogger(HooverService.class);

    private final MovementService movementService;
    private final CleaningService cleaningService;

    @Autowired
    public HooverService(MovementService movementService, CleaningService cleaningService) {
        this.movementService = movementService;
        this.cleaningService = cleaningService;
    }

    public HooverResponse processHooverMovement(HooverRequest request) {
        logger.info("Processing hoover movement request");
        validateRequest(request);

        Room room = new Room(request.getRoomSize(), request.getPatches());
        Hoover hoover = new Hoover(request.getCoords());

        int dirtCleaned = 0;

        // Clean the initial position if there's dirt
        if (cleaningService.cleanDirt(room, hoover)) {
            dirtCleaned++;
        }

        for (char instruction : request.getInstructions().toCharArray()) {
            hoover = movementService.moveHoover(instruction, hoover, room);
            if (cleaningService.cleanDirt(room, hoover)) {
                dirtCleaned++;
            }
        }
        logger.info("Hoover movement completed. Final position: [{}, {}], Patches cleaned: {}",
                hoover.getX(), hoover.getY(), dirtCleaned);
        return new HooverResponse(hoover.getCoords(), dirtCleaned);
    }

    private void validateRequest(HooverRequest request) {
        if (isOutOfBounds(request.getCoords(), request.getRoomSize())) {
            throw new OutOfBoundsException("Initial hoover position is outside the room");
        }

        for (int[] patch : request.getPatches()) {
            if (isOutOfBounds(patch, request.getRoomSize())) {
                throw new OutOfBoundsException("Dirt patch position is outside the room");
            }
        }
    }

    private boolean isOutOfBounds(int[] coords, int[] roomSize) {
        return coords[0] < 0 || coords[1] < 0 || coords[0] >= roomSize[0] || coords[1] >= roomSize[1];
    }
}