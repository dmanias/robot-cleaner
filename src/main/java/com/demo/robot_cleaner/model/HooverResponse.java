package com.demo.robot_cleaner.model;

import lombok.Getter;

@Getter
public class HooverResponse {
    private final int[] coords;
    private final int patches;

    public HooverResponse(int[] coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    public int[] getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }

}
