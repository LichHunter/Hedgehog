package com.hedgehog;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

        return new EqualsBuilder().append(distanceFromStart, node.distanceFromStart).append(heuristicDistance, node.heuristicDistance).append(coordinate, node.coordinate).append(previous, node.previous).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(distanceFromStart).append(heuristicDistance).append(coordinate).append(previous).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("distanceFromStart", distanceFromStart)
            .append("heuristicDistance", heuristicDistance)
            .append("coordinate", coordinate)
            .toString();
    }
}
