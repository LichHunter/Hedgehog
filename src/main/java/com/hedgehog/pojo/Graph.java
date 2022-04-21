package com.hedgehog.pojo;

import com.hedgehog.Coordinate;
import com.hedgehog.exception.NoNodeFoundException;

import java.util.Set;

public record Graph<T extends Node>(
    Set<T> nodes
) {
    public T getNodeByCoordinate(Coordinate coordinate) {
        return nodes
            .stream()
            .filter(node -> coordinate.equals(node.coordinate()))
            .findFirst()
            .orElseThrow(() -> new NoNodeFoundException("Nothing found for coordinate: " + coordinate));
    }
}
