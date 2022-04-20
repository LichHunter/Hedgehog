package com.hedgehog;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class AStart extends PathFinder {

    public AStart(Map<Coordinate, Node> open, Set<Node> closed) {
        super(open, closed);
    }

    public AStart() {
        super();
    }

    @Override
    public Node findBestPath(Node current, Node end) {
        for (Node neighbour : current.neighbours()) {
            if (end.coordinate().equals(neighbour.coordinate())) {
                return neighbour;
            }

            var inOpen = super.open.get(neighbour.coordinate());
            if (inOpen != null) {
                if (neighbour.getF() > inOpen.getF()) {
                    super.open.put(neighbour.coordinate(), neighbour);
                }
            } else {
                super.open.put(neighbour.coordinate(), neighbour);
            }
        }

        super.closed.add(current);
        super.open.remove(current.coordinate());

        var max = open.values().stream()
            .max(Comparator.comparing(Node::getF));
        return findBestPath(max.get(), end);
    }
}
