package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CleaningServiceTest {

    private CleaningService cleaningService;
    private Room room;
    private Hoover hoover;

    @BeforeEach
    void setUp() {
        cleaningService = new CleaningService();
        room = new Room(new int[]{5, 5}, List.of(new int[]{1, 1}, new int[]{3, 3}));
        hoover = new Hoover(new int[]{0, 0});
    }

    @Test
    void cleanDirt_DirtPresent_ReturnsTrue() {
        hoover.setX(1);
        hoover.setY(1);
        assertTrue(cleaningService.cleanDirt(room, hoover));
    }

    @Test
    void cleanDirt_NoDirtPresent_ReturnsFalse() {
        assertFalse(cleaningService.cleanDirt(room, hoover));
    }

    @Test
    void cleanDirt_MultipleCalls_OnlyFirstReturnsTrue() {
        hoover.setX(1);
        hoover.setY(1);
        assertTrue(cleaningService.cleanDirt(room, hoover));
        assertFalse(cleaningService.cleanDirt(room, hoover));
    }
}