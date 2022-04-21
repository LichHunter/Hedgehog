package com.hedgehog;

import com.hedgehog.pojo.Coordinate;
import com.hedgehog.pojo.Node;

import java.util.*;

public class AStar<T extends Node> implements PathFinder<T> {
    protected Map<Coordinate, T> open;
    protected Set<T> closed;

    public AStar(Map<Coordinate, T> open, Set<T> closed) {
        this.open = open;
        this.closed = closed;
    }

    public AStar() {
        this.open = new HashMap<>();
        this.closed = new HashSet<>();
    }

    @Override
    public T findBestPath(T current, T end) {
        for (Node neighbour : current.getNeighbours()) {
            if (end.getCoordinate().equals(neighbour.getCoordinate())) {
                return (T) neighbour;
            }

            T inOpen = open.get(neighbour.getCoordinate());
            if (inOpen != null) {
                if (neighbour.getF() > inOpen.getF()) {
                    open.put(neighbour.getCoordinate(), (T) neighbour);
                }
            } else {
                open.put(neighbour.getCoordinate(), (T) neighbour);
            }
        }

        closed.add(current);
        open.remove(current.getCoordinate());

        T max = open.values()
            .stream()
            .max(Comparator.comparing(Node::getF))
            .orElseThrow(() -> new RuntimeException("Open collection is empty, check recurstion"));
        return findBestPath(max, end);
    }
}
