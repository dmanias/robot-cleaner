package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.Room;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class MockMovementService extends MovementService {

    @Override
    public void moveHoover(char direction, Hoover hoover, Room room) {
        // Mock implementation: Do nothing, so the hoover doesn't move.
        System.out.println("MockMovementService: Ignoring move command");
    }
}
