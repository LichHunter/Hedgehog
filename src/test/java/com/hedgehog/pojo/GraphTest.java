package com.hedgehog.pojo;

import com.hedgehog.exception.NoNodeFoundException;
import com.hedgehog.util.GraphBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphTest {

    @Test
    void getNodeByCoordinate_returnsNode() {
        var expected = new Node(4, 0,
            new HashSet<>(),
            new Coordinate(1, 1),
            null);

        var grid = new Integer[][]{{1, 2}, {3, 4}};
        var graph = new GraphBuilder().buildFrom(grid);
        var actual = graph.getNodeByCoordinate(new Coordinate(1, 1));

        assertThat(actual, is(expected));
    }

    @Test
    void getNodeByCoordinate_throwsException() {
        var grid = new Integer[][]{{1, 2}, {3, 4}};
        var graph = new GraphBuilder().buildFrom(grid);
        assertThrows(NoNodeFoundException.class, () -> graph.getNodeByCoordinate(new Coordinate(2, 2)));
    }
}