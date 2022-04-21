package com.hedgehog.util;

import com.hedgehog.exception.FileIsEmptyException;
import com.hedgehog.exception.InvalidNumberOfLinesException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.util.Optional;

@Log4j2
public class GridReader {
    public Optional<Integer[][]> readGridFromFile(File file) {
        try {
            return Optional.of(readGridFromFile(new BufferedReader(new FileReader(file))));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    protected Integer[][] readGridFromFile(Reader reader)
        throws IOException, InvalidNumberOfLinesException, FileIsEmptyException {
        try (var bufferedReader = new BufferedReader(reader)) {
            String firstLine = bufferedReader.readLine();
            if (StringUtils.isBlank(firstLine)) {
                throw new FileIsEmptyException("First line is empty");
            }
            var heightAndWith = firstLine.split("\\s+");
            var height = NumberUtils.toInt(heightAndWith[0], 0);
            var width = NumberUtils.toInt(heightAndWith[1], 0);
            Integer[][] grid = new Integer[height][width];

            String line;
            var i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                var apples = line.split("\\s+");

                if (apples.length != width) {
                    throw new InvalidNumberOfLinesException(String.format("Expected number of columns: %d, actual: %d",
                        width, apples.length));
                }

                for (int j = 0; j < width; j++) {
                    grid[i][j] = NumberUtils.toInt(apples[j], 0);
                }
                i++;
            }

            if (i != height) {
                throw new InvalidNumberOfLinesException(String.format("Expected: %d, actual: %d", height, i));
            }
            if (isEmpty(grid)) {
                throw new FileIsEmptyException(String.format("Expected %d rows and %d columns but non was found",
                    height, width));
            }

            return grid;
        }
    }

    protected boolean isEmpty(Integer[][] grid) {
        if (grid == null) {
            return true;
        }
        for (Integer[] row : grid) {
            for (Integer cell : row) {
                if (cell != null) {
                    return false;
                }
            }
        }

        return true;
    }
}
