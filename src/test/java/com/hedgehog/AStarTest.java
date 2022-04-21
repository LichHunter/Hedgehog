package com.hedgehog;

import com.hedgehog.pojo.Coordinate;
import com.hedgehog.pojo.Node;
import com.hedgehog.util.GraphBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AStarTest {
    @ParameterizedTest
    @CsvSource({"3 3 1 2 1 1 5 1 1 8 9,25",
        "3 3 1 2 3 4 5 6 7 8 9,29",
        "2 2 1 2 3 4,8"})
    void findBestPath(String input, Integer expected) {
        var grid = createGridFromString(input);
        var graph = new GraphBuilder().buildFrom(grid);
        var start = graph.getNodeByCoordinate(new Coordinate(0, 0));
        var end = graph.getNodeByCoordinate(new Coordinate(grid.length - 1, grid[0].length - 1));
        Map<Coordinate, Node> open = new HashMap<>();
        Set<Node> closed = new HashSet<>();
        open.put(start.getCoordinate(), start);

        var actual = new AStar<>(open, closed).findBestPath(start, end);
        assertThat(actual.getDistanceFromStart(), is(expected));
    }

    private Integer[][] createGridFromString(String input) {
        var nums = input.split("\\s+");
        var height = NumberUtils.toInt(nums[0]);
        var width = NumberUtils.toInt(nums[1]);

        var grid = new Integer[height][width];
        var currentY = 0;
        var currentX = 0;
        //i = 2 -> because first two numbers are height and width, so we skip them
        for (int i = 2; i < nums.length; i++) {
            var num = nums[i];
            if (currentX < width) {
                grid[currentY][currentX++] = NumberUtils.toInt(num);
            } else {
                currentX = 0;
                grid[++currentY][currentX++] = NumberUtils.toInt(num);
            }
        }
        return grid;
    }
}