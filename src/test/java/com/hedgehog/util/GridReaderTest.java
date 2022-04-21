package com.hedgehog.util;

import com.hedgehog.exception.FileIsEmptyException;
import com.hedgehog.exception.InvalidNumberOfLinesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class GridReaderTest {
    private GridReader gridReader;

    @BeforeEach
    void setUp() {
        this.gridReader = new GridReader();
    }

    @Test
    void readGridFromFile_noFile() {
        assertThrows(NullPointerException.class, () -> gridReader.readGridFromFile((Reader) null));
    }

    @Test
    void readGridFromFile_emptyFile() {
        var file = """
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        assertThrows(FileIsEmptyException.class, () -> gridReader.readGridFromFile(reader));
    }

    @Test
    void readGridFromFile_returnsData() throws IOException {
        var expected = new Integer[][]{{1}};

        var file = """
            1 1
            1
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        var actual = gridReader.readGridFromFile(reader);
        assertThat(actual, is(expected));
    }

    @Test
    void readGridFromFile_invalidNumberOfColumns() {
        var file = """
            1 10
            1
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        assertThrows(InvalidNumberOfLinesException.class, () -> gridReader.readGridFromFile(reader));
    }

    @Test
    void readGridFromFile_invalidNumberOfRows() {
        var file = """
            10 3
            1 2 3
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        assertThrows(InvalidNumberOfLinesException.class, () -> gridReader.readGridFromFile(reader));
    }

    @Test
    void isEmpty_true() {
        var nums = new Integer[][]{{null, null}, {null, null}};
        assertTrue(gridReader.isEmpty(nums));
    }

    @Test
    void isEmpty_false() {
        var nums = new Integer[][]{{null, null}, {1, null}};
        assertFalse(gridReader.isEmpty(nums));
    }

    @Test
    void isEmpty_fromNullArray() {
        assertTrue(gridReader.isEmpty(null));
    }
}