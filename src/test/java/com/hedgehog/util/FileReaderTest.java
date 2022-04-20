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

class FileReaderTest {
    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        this.fileReader = new FileReader();
    }

    @Test
    void readFile_noFile() {
        assertThrows(NullPointerException.class, () -> fileReader.readFile((Reader) null));
    }

    @Test
    void readFile_emptyFile() {
        var file = """
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        assertThrows(FileIsEmptyException.class, () -> fileReader.readFile(reader));
    }

    @Test
    void readFile_returnsData() throws IOException {
        var expected = new Integer[][]{{1}};

        var file = """
            1 1
            1
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        var actual = fileReader.readFile(reader);
        assertThat(actual, is(expected));
    }

    @Test
    void readFile_invalidNumberOfColumns() {
        var file = """
            1 10
            1
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        assertThrows(InvalidNumberOfLinesException.class, () -> fileReader.readFile(reader));
    }

    @Test
    void readFile_invalidNumberOfRows() {
        var file = """
            10 3
            1 2 3
            """;
        var reader = new InputStreamReader(new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8)));
        assertThrows(InvalidNumberOfLinesException.class, () -> fileReader.readFile(reader));
    }

    @Test
    void isEmpty_true() {
        var nums = new Integer[][]{{null, null}, {null, null}};
        assertTrue(fileReader.isEmpty(nums));
    }

    @Test
    void isEmpty_false() {
        var nums = new Integer[][]{{null, null}, {1, null}};
        assertFalse(fileReader.isEmpty(nums));
    }

    @Test
    void isEmpty_fromNullArray() {
        assertTrue(fileReader.isEmpty(null));
    }
}