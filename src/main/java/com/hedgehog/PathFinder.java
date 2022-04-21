package com.hedgehog;

import com.hedgehog.pojo.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PathFinder<T extends Node> {
    protected Map<Coordinate, T> open;
    protected Set<T> closed;

    protected PathFinder(Map<Coordinate, T> open, Set<T> closed) {
        this.open = open;
        this.closed = closed;
    }

    protected PathFinder() {
        this.open = new HashMap<>();
        this.closed = new HashSet<>();
    }

    public abstract T findBestPath(T current, T end);
}
