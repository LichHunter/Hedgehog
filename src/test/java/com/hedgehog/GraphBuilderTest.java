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
        var expected = new Node(0, new HashSet<>(),
            new Coordinate(0, 0), 0);
        var actual = builder.create(0, 0, new Coordinate(0, 0), 0, 0, grid);
        assertThat(actual, is(expected));
    }

    @Test
    void create_withBottomNeighbour() {
        var grid = new Integer[][]{{1}, {2}};
        var endCoordinate = new Coordinate(0, 0);
        var neighbours = Set.of(
            new Node(1, new HashSet<>(), new Coordinate(1, 0), 2)
        );
        var expected = new Node(0, neighbours, new Coordinate(0, 0), 1);

        var actual = builder.create(0, 0, endCoordinate, 1, 0, grid);

        assertThat(actual, is(expected));
    }

    @Test
    void create_withRightNeighbour() {
        var grid = new Integer[][]{{1, 2}};
        var endCoordinate = new Coordinate(0, 0);
        var neighbours = Set.of(
            new Node(1, new HashSet<>(), new Coordinate(0, 1), 2)
        );
        var expected = new Node(0, neighbours, new Coordinate(0, 0), 1);

        var actual = builder.create(0, 0, endCoordinate, 0, 1, grid);

        assertThat(actual, is(expected));
    }

    @Test
    void buildFrom() {
        var nodes = Set.of(new Node(0, new HashSet<>(), new Coordinate(0, 0), 0));
        var expected = new Graph(nodes);

        var actual = builder.buildFrom(new Integer[][]{{0}});

        assertThat(actual, is(expected));
    }
}