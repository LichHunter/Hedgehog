package com.hedgehog;

import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {
    public Graph buildFrom(Integer[][] grid) {
        Set<Node> nodes = new HashSet<>();
        var endPoint = new Coordinate(grid.length - 1, grid[0].length - 1);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                var node = create(i, j, endPoint,
                    grid.length - 1, grid[0].length - 1, grid);
                nodes.add(node);
            }
        }

        return new Graph(nodes);
    }

    protected Node create(int i, int j, Coordinate endPoint,
                          int maxI, int maxJ, Integer[][] grid) {
        Coordinate current = new Coordinate(i, j);
        var heuristicDistance = current.getDistanceTo(endPoint);
        Set<Node> neighbours = new HashSet<>();
        if (i < maxI) {
            Node bottomNeighbour = create(i + 1, j, endPoint, maxI, maxJ, grid);
            neighbours.add(bottomNeighbour);
        }
        if (j < maxJ) {
            Node rightNeighbour = create(i, j + 1, endPoint, maxI, maxJ, grid);
            neighbours.add(rightNeighbour);
        }

        return new Node(heuristicDistance, neighbours, current, grid[i][j]);
    }
}
