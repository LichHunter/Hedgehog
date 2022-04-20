package com.hedgehog;

import com.hedgehog.util.FileReader;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
public class App {
    public static void main(String[] args) {
        log.info("Hello");
        var inputFile = args[0];
        var outputFile = args[1];

        var optionalGrid = new FileReader().readFile(inputFile);
        if (optionalGrid.isPresent()) {
            var grid = optionalGrid.get();
            var graph = new GraphBuilder().buildFrom(grid);

            //todo rename
            var startOptional = graph
                .nodes()
                .stream()
                .filter(node -> new Coordinate(0, 0).equals(node.coordinate()))
                .findFirst();

            if (startOptional.isPresent()) {
                var start = startOptional.get();
                List<Node> open = new ArrayList<>();
                Set<Node> closed = new HashSet<>();

                open.add(start);

                open.addAll(start.neighbours());


            } else {
                log.warn("Was not able to get start point from graph: {}", graph);
            }

        } else {
            log.warn("Was not able to get grid from file");
        }
    }

}
