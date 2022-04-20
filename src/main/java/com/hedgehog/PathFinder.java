package com.hedgehog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PathFinder {
    protected Map<Coordinate, Node> open;
    protected Set<Node> closed;

    protected PathFinder(Map<Coordinate, Node> open, Set<Node> closed) {
        this.open = open;
        this.closed = closed;
    }

    protected PathFinder() {
        this.open = new HashMap<>();
        this.closed = new HashSet<>();
    }

    public abstract Node findBestPath(Node current, Node end);
}
