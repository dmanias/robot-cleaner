package com.demo.robot_cleaner.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
    private final int width;
    private final int height;
    private final Set<DirtPatch> dirtPatches;

    public Room(int[] roomSize, List<int[]> patches) {
        this.width = roomSize[0];
        this.height = roomSize[1];
        this.dirtPatches = new HashSet<>();
        for (int[] patch : patches) {
            dirtPatches.add(new DirtPatch(patch[0], patch[1]));
        }
    }

    public boolean cleanDirt(int x, int y) {
        return dirtPatches.remove(new DirtPatch(x, y));
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
