package com.hedgehog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

@Data
@AllArgsConstructor
public class Node {
    private final int distanceFromStart;
    private final int heuristicDistance;
    private final Set<Node> neighbours;
    private final Coordinate coordinate;
    private final Node previous;

    public int getF() {
        return heuristicDistance + distanceFromStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return new EqualsBuilder().append(heuristicDistance, node.heuristicDistance).append(coordinate, node.coordinate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(heuristicDistance).append(coordinate).toHashCode();
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
