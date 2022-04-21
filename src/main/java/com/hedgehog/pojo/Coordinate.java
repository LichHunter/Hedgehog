package com.hedgehog.pojo;

import lombok.Data;

@Data
public class Coordinate {
    private final int y;
    private final int x;

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getDistanceTo(Coordinate coordinate) {
        if (coordinate == null) {
            return 0;
        }
        return Math.abs(coordinate.getX() - this.x) + Math.abs(coordinate.getY() - this.y);
    }
}
