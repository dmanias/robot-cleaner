package com.demo.robot_cleaner.service;

import com.demo.robot_cleaner.model.Hoover;
import com.demo.robot_cleaner.model.Room;
import org.springframework.stereotype.Service;

@Service
public class CleaningService {

    public boolean cleanDirt(Room room, Hoover hoover) {
        return room.cleanDirt(hoover.getX(), hoover.getY());
    }
}
