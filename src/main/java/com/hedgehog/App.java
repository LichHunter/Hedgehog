package com.hedgehog;

import com.hedgehog.util.FileReader;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Log4j2
public class App {
    public static void main(String[] args) {
        log.info("Hello");
        var inputFile = args[0];
        var outputFile = args[1];

        var grid = new FileReader().readFile(inputFile)
            .orElseThrow(() -> new RuntimeException("No grid was found"));
        var graph = new GraphBuilder().buildFrom(grid);
        var start = graph.getNodeByCoordinate(new Coordinate(0, 0));
        var end = graph.getNodeByCoordinate(new Coordinate(grid.length - 1, grid[0].length - 1));

        Map<Coordinate, Node> open = new HashMap<>();
        Set<Node> closed = new HashSet<>();

        open.put(start.coordinate(), start);

        PathFinder<Node> pathFinder = new AStar<>(open, closed);

        log.info(pathFinder.findBestPath(start, end));
    }
}
