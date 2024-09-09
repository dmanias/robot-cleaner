package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovementServiceTest {

    private MovementService movementService;
    private Hoover hoover;
    private Room room;

    @BeforeEach
    void setUp() {
        movementService = new MovementService();
        room = new Room(new int[]{5, 5}, List.of());
        hoover = new Hoover(new int[]{2, 2});
    }

    @Test
    void moveHoover_North_Success() {
        movementService.moveHoover('N', hoover, room);
        assertArrayEquals(new int[]{2, 3}, hoover.getCoords());
    }

    @Test
    void moveHoover_South_Success() {
        movementService.moveHoover('S', hoover, room);
        assertArrayEquals(new int[]{2, 1}, hoover.getCoords());
    }

    @Test
    void moveHoover_East_Success() {
        movementService.moveHoover('E', hoover, room);
        assertArrayEquals(new int[]{3, 2}, hoover.getCoords());
    }

    @Test
    void moveHoover_West_Success() {
        movementService.moveHoover('W', hoover, room);
        assertArrayEquals(new int[]{1, 2}, hoover.getCoords());
    }

    @Test
    void moveHoover_NorthAtTopEdge_StaysInPlace() {
        hoover = new Hoover(new int[]{2, 4});
        movementService.moveHoover('N', hoover, room);
        assertArrayEquals(new int[]{2, 4}, hoover.getCoords());
    }

    @Test
    void moveHoover_InvalidDirection_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> movementService.moveHoover('X', hoover, room));
    }
}