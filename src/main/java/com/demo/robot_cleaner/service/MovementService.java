package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.Room;
import org.springframework.stereotype.Service;

@Service
public class MovementService {

    public void moveHoover(char direction, Hoover hoover, Room room) {
        int newX = hoover.getX();
        int newY = hoover.getY();

        switch (direction) {
            case 'N': // Move north
                newY = Math.min(hoover.getY() + 1, room.getHeight() - 1);
                break;
            case 'S': // Move south
                newY = Math.max(hoover.getY() - 1, 0);
                break;
            case 'E': // Move east
                newX = Math.min(hoover.getX() + 1, room.getWidth() - 1);
                break;
            case 'W': // Move west
                newX = Math.max(hoover.getX() - 1, 0);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }

        hoover.setX(newX);
        hoover.setY(newY);
    }
}