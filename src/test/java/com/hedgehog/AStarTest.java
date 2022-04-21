package com.hedgehog;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AStarTest {

    @Test
    void findBestPath() {
        var grid = new Integer[][]{{1, 2, 1}, {1, 5, 1}, {1, 8, 9}};
        var graph = new GraphBuilder().buildFrom(grid);
        var start = graph.getNodeByCoordinate(new Coordinate(0, 0));
        var end = graph.getNodeByCoordinate(new Coordinate(grid.length - 1, grid[0].length - 1));

        Map<Coordinate, Node> open = new HashMap<>();
        Set<Node> closed = new HashSet<>();

        open.put(start.coordinate(), start);

        var actual = new AStar(open, closed).findBestPath(start, end);
        assertThat(actual.distanceFromStart(), is(25));
    }
}