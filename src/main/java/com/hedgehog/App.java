package com.hedgehog;

import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2
public class App {
    public static void main(String[] args) {
        log.info("Hello");
//        var inputFile = args[0];
//        var outputFile = args[1];

//        var optionalGrid = new FileReader().readFile(inputFile);
        var optionalGrid = Optional.of(new Integer[][]{{1, 2, 1}, {1, 5, 1}, {1, 8, 9}});
        if (optionalGrid.isPresent()) {
            var grid = optionalGrid.get();
            var graph = new GraphBuilder().buildFrom(grid);

            var start = graph
                .nodes()
                .stream()
                .filter(node -> new Coordinate(0, 0).equals(node.coordinate()))
                .findFirst()
                .get();
            var end = graph
                .nodes()
                .stream()
                .filter(node -> new Coordinate(grid.length - 1, grid[0].length - 1).equals(node.coordinate()))
                .findFirst()
                .get();

            Map<Coordinate, Node> open = new HashMap<>();
            Set<Node> closed = new HashSet<>();

            open.put(start.coordinate(), start);

            PathFinder pathFinder = new AStart(open, closed);

            log.info(pathFinder.findBestPath(start, end));
        } else {
            log.warn("Was not able to get grid from file");
        }
    }
}
