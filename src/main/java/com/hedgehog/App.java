package com.hedgehog;

import com.hedgehog.util.FileReader;
import lombok.extern.log4j.Log4j2;

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
        } else {
            log.warn("Was not able to get grid from file");
        }
    }

}
