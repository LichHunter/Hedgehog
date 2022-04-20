package com.hedgehog;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Set;

public record Node(
    int distanceFromStart,
    int heuristicDistance,
    Set<Node> neighbours,
    Coordinate coordinate,
    Node previous
) {
    public int getF() {
        return heuristicDistance + distanceFromStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder().append(coordinate, node.coordinate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(coordinate).toHashCode();
    }
}
