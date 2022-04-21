package com.hedgehog;

import com.hedgehog.pojo.Coordinate;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CoordinateTest {
    @Test
    void getDistanceTo() {
        var end = new Coordinate(1, 1);
        var current = new Coordinate(0, 0);
        assertThat(current.getDistanceTo(end), is(2));
    }

    @Test
    void getDistanceTo_itself() {
        var current = new Coordinate(0, 0);
        assertThat(current.getDistanceTo(current), is(0));
    }

    @Test
    void getDistanceTo_nullSafe() {
        var current = new Coordinate(0, 0);
        assertThat(current.getDistanceTo(null), is(0));
    }

    @Test
    void getDistanceTo_coordinateThatBeforeCurrent() {
        var current = new Coordinate(1, 1);
        assertThat(current.getDistanceTo(new Coordinate(0, 0)), is(2));
    }
}