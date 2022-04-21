package com.hedgehog.util;

import com.hedgehog.pojo.Coordinate;
import com.hedgehog.pojo.Graph;
import com.hedgehog.pojo.Node;

import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {
    public Graph<Node> buildFrom(Integer[][] grid) {
        Set<Node> nodes = new HashSet<>();
        var endPoint = new Coordinate(grid.length - 1, grid[0].length - 1);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                var node = create(i, j, endPoint, grid, null);
                nodes.add(node);
            }
        }

        return new Graph<>(nodes);
    }

    protected Node create(int i, int j, Coordinate endPoint, Integer[][] grid, Node previous) {
        int maxI = grid.length - 1;
        int maxJ = grid[0].length - 1;
        Coordinate current = new Coordinate(i, j);
        var heuristicDistance = current.getDistanceTo(endPoint);
        var value = grid[i][j];

        int previousDistanceFromStart = previous == null ? 0 : previous.getDistanceFromStart();
        var currentNode = new Node(value + previousDistanceFromStart,
            heuristicDistance, new HashSet<>(), current, previous);

        if (i < maxI) {
            Node bottomNeighbour = create(i + 1, j, endPoint, grid, currentNode);
            currentNode.getNeighbours().add(bottomNeighbour);
        }
        if (j < maxJ) {
            Node rightNeighbour = create(i, j + 1, endPoint, grid, currentNode);
            currentNode.getNeighbours().add(rightNeighbour);
        }

        return currentNode;
    }
}
