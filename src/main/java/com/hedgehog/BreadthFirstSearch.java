package com.hedgehog;

import com.hedgehog.pojo.Graph;
import com.hedgehog.pojo.Node;

import java.util.*;

public class BreadthFirstSearch implements PathFinder<Node> {
    private final Queue<Node> queue;

    public BreadthFirstSearch(Graph<Node> graph) {
        this.queue = new LinkedList<>(graph.getNodes());
    }

    @Override
    public Node findBestPath(Node current, Node end) {
        List<Node> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            var node = queue.poll();
            if (end.getCoordinate().equals(node.getCoordinate())) {
                list.add(node);
            } else {
                queue.addAll(node.getNeighbours());
            }
        }

        return list.stream()
            .max(Comparator.comparing(Node::getDistanceFromStart))
            .orElseThrow(() -> new IllegalStateException("Was not able to get any nodes"));
    }
}
