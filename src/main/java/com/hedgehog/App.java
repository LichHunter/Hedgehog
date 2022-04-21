package com.hedgehog;

import com.hedgehog.pojo.Coordinate;
import com.hedgehog.pojo.Node;
import com.hedgehog.util.GraphBuilder;
import com.hedgehog.util.GridReader;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Log4j2
public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalStateException("There must be only 2 input arguments");
        }
        var inputFilePath = args[0];
        var outputFilePath = args[1];

        var grid = new GridReader()
            .readGridFromFile(inputFilePath)
            .orElseThrow(() -> new RuntimeException("No grid was found"));
        var graph = new GraphBuilder().buildFrom(grid);
        var start = graph.getNodeByCoordinate(new Coordinate(0, 0));
        var end = graph.getNodeByCoordinate(new Coordinate(grid.length - 1, grid[0].length - 1));

        Map<Coordinate, Node> open = new HashMap<>();
        Set<Node> closed = new HashSet<>();

        open.put(start.getCoordinate(), start);

        PathFinder<Node> pathFinder = new AStar<>(open, closed);

        Node bestPath = pathFinder.findBestPath(start, end);
        log.info(bestPath);

        saveToFile(outputFilePath, bestPath.getDistanceFromStart());
    }

    private static void saveToFile(String outputFilePath, Integer distanceFromStart) {
        var file = new File(outputFilePath);
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return;
            }
        }

        try (var writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(String.valueOf(distanceFromStart));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
