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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class App {

    public static final String INPUT_FILE_NAME = "input.txt" ;
    public static final String OUTPUT_FILE_NAME = "output.txt" ;

    public static void main(String[] args) {
        var grid = new GridReader()
            .readGridFromFile(new File(INPUT_FILE_NAME))
            .orElseThrow(() -> new RuntimeException("No grid was found"));
        var graph = new GraphBuilder().buildFrom(grid);
        var start = graph.getNodeByCoordinate(new Coordinate(0, 0));
        var end = graph.getNodeByCoordinate(new Coordinate(grid.length - 1, grid[0].length - 1));

        Node bestPath = new BreadthFirstSearch(graph).findBestPath(start, end);
        log.info(bestPath);

        showPath(bestPath);
        saveToFile(new File(OUTPUT_FILE_NAME), bestPath.getDistanceFromStart());
    }

    private static void showPath(Node bestPath) {
        List<String> buffer = new ArrayList<>();
        var current = bestPath;
        while (true) {
            buffer.add(String.format("(%d:%d):%d",
                current.getCoordinate().getY(),
                current.getCoordinate().getX(),
                current.getDistanceFromStart()));

            var previous = current.getPrevious();
            if (previous != null) {
                current = previous;
            } else {
                break;
            }
        }
        Collections.reverse(buffer);

        log.info("Path: {}", String.join("->", buffer));
    }

    private static void saveToFile(File file, Integer distanceFromStart) {
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
