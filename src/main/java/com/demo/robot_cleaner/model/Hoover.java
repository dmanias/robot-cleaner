package com.demo.robot_cleaner.model;

import lombok.Setter;
import lombok.Getter;


@Setter
@Getter
public class Hoover {
    private int x;
    private int y;

    public Hoover(int[] initialPosition) {
        this.x = initialPosition[0];
        this.y = initialPosition[1];
    }

    public void move(char direction, Room room) {
        switch (direction) {
            case 'N':
                if (y < room.getHeight() - 1) y++;
                break;
            case 'S':
                if (y > 0) y--;
                break;
            case 'E':
                if (x < room.getWidth() - 1) x++;
                break;
            case 'W':
                if (x > 0) x--;
                break;
        }
    }

    public int[] getCoords() {
        return new int[]{x, y};
    }
}
