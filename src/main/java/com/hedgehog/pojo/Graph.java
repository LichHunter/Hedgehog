package com.hedgehog.pojo;

import com.hedgehog.exception.NoNodeFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Graph<T extends Node> {
    private Set<T> nodes;

    public T getNodeByCoordinate(Coordinate coordinate) {
        return nodes
            .stream()
            .filter(node -> coordinate.equals(node.getCoordinate()))
            .findFirst()
            .orElseThrow(() -> new NoNodeFoundException("Nothing found for coordinate: " + coordinate));
    }
}
