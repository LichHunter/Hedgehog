package com.hedgehog;

public record Coordinate(
    int y,
    int x
) {
    public int getDistanceTo(Coordinate coordinate) {
        if (coordinate == null) {
            return 0;
        }
        return Math.abs(coordinate.x() - this.x) + Math.abs(coordinate.y() - this.y);
    }
}
