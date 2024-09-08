package com.demo.robot_cleaner.model;

import java.util.Objects;

public class DirtPatch {
    private final int x;
    private final int y;

    public DirtPatch(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirtPatch dirtPatch = (DirtPatch) o;
        return x == dirtPatch.x && y == dirtPatch.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
