package com.hedgehog;

import lombok.Data;

import java.util.Set;

@Data
public class Node {
    private final int heuristicDistance;
    private final Set<Node> neighbours;
    private final Coordinate coordinate;
    private final int value;
    private int distanceFromStart;
    private int f;//distanceFromStart + heuristicDistance
    private Node previous;

    public int getF() {
        return heuristicDistance + distanceFromStart;
    }
}
