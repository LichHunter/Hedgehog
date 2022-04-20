package com.hedgehog;

import com.hedgehog.util.FileReader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class App {
    public static void main(String[] args) {
        log.info("Hello");
        var inputFile = args[0];
        var outputFile = args[1];

        var grid = new FileReader().readFile(inputFile);

    }
}
