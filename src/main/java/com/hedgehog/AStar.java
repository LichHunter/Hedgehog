package com.hedgehog;

import com.hedgehog.pojo.Node;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class AStar<T extends Node> extends PathFinder<T> {

    public AStar(Map<Coordinate, T> open, Set<T> closed) {
        super(open, closed);
    }

    public AStar() {
        super();
    }

    @Override
    public T findBestPath(T current, T end) {
        for (Node neighbour : current.neighbours()) {
            if (end.coordinate().equals(neighbour.coordinate())) {
                return (T) neighbour;
            }

            T inOpen = super.open.get(neighbour.coordinate());
            if (inOpen != null) {
                if (neighbour.getF() > inOpen.getF()) {
                    super.open.put(neighbour.coordinate(), (T) neighbour);
                }
            } else {
                super.open.put(neighbour.coordinate(), (T) neighbour);
            }
        }

        super.closed.add(current);
        super.open.remove(current.coordinate());

        T max = open.values()
            .stream()
            .max(Comparator.comparing(Node::getF))
            .orElseThrow(() -> new RuntimeException("Open collection is empty, check recurstion"));
        return findBestPath(max, end);
    }
}
