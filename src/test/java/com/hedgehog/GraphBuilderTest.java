package com.hedgehog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class GraphBuilderTest {
    private GraphBuilder builder;

    @BeforeEach
    void setUp() {
        this.builder = new GraphBuilder();
    }

    @Test
    void create() {
        var grid = new Integer[][]{{0}};
        var expected = new Node(0, 0, new HashSet<>(),
            new Coordinate(0, 0), null);
        var actual = builder.create(0, 0, new Coordinate(0, 0), 0, 0, grid, null);
        assertThat(actual, is(expected));
    }

    @Test
    void create_withBottomNeighbour() {
        var grid = new Integer[][]{{1}, {2}};
        var endCoordinate = new Coordinate(0, 0);
        var expected = new Node(1, 0, new HashSet<>(), new Coordinate(0, 0), null);
        expected.neighbours().add(new Node(3, 1, new HashSet<>(), new Coordinate(1, 0), expected));

        var actual = builder.create(0, 0, endCoordinate, 1, 0, grid, null);

        assertThat(actual, is(expected));
    }

    @Test
    void create_withRightNeighbour() {
        var grid = new Integer[][]{{1, 2}};
        var endCoordinate = new Coordinate(0, 0);
        var expected = new Node(1, 0, new HashSet<>(), new Coordinate(0, 0), null);
        expected.neighbours().add(new Node(3, 1, new HashSet<>(), new Coordinate(0, 1), expected));

        var actual = builder.create(0, 0, endCoordinate, 0, 1, grid, null);

        assertThat(actual, is(expected));
    }

    @Test
    void buildFrom() {
        var nodes = Set.of(new Node(0, 0, new HashSet<>(), new Coordinate(0, 0), null));
        var expected = new Graph(nodes);

        var actual = builder.buildFrom(new Integer[][]{{0}});

        assertThat(actual, is(expected));
    }

    @Test
    void buildFrom_graphContainsSeveralNodesForSameCoordinate() {
        int heuristicDistance = 2;
        var nodeA = new Node(1, heuristicDistance, new HashSet<>(), new Coordinate(0, 0),
            null);
        Node nodeAB = new Node(3, 1, new HashSet<>(), new Coordinate(0, 1), nodeA);
        nodeAB.neighbours().add(new Node(7, 0, new HashSet<>(), new Coordinate(1, 1), nodeAB));
        nodeA.neighbours().add(nodeAB);
        Node nodeAC = new Node(4, 1, new HashSet<>(), new Coordinate(1, 0), nodeA);
        nodeAC.neighbours().add(new Node(8, 0, new HashSet<>(), new Coordinate(1, 1), nodeAC));
        nodeA.neighbours().add(nodeAC);

        var nodeB = new Node(2, 1, new HashSet<>(), new Coordinate(0, 1), null);
        nodeB.neighbours().add(new Node(6, 0, new HashSet<>(), new Coordinate(1, 1), nodeB));

        var nodeC = new Node(3, 1, new HashSet<>(), new Coordinate(1, 0), null);
        nodeC.neighbours().add(new Node(7, 0, new HashSet<>(), new Coordinate(1, 1), nodeC));

        var nodeD = new Node(4, 0, new HashSet<>(), new Coordinate(1, 1), null);

        var nodes = Set.of(nodeA, nodeB, nodeC, nodeD);
        var expected = new Graph(nodes);

        var actual = builder.buildFrom(new Integer[][]{{1, 2}, {3, 4}});

        assertThat(actual, is(expected));
    }
}