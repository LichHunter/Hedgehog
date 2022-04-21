package com.hedgehog.pojo;

import com.hedgehog.Coordinate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

public class Node {
    private final int distanceFromStart;
    private final int heuristicDistance;
    private final Set<Node> neighbours;
    private final Coordinate coordinate;
    private final Node previous;

    public Node(int distanceFromStart, int heuristicDistance, Set<Node> neighbours,
                Coordinate coordinate, Node previous) {
        this.distanceFromStart = distanceFromStart;
        this.heuristicDistance = heuristicDistance;
        this.neighbours = neighbours;
        this.coordinate = coordinate;
        this.previous = previous;
    }

    public int getF() {
        return heuristicDistance + distanceFromStart;
    }

    public int distanceFromStart() {
        return distanceFromStart;
    }

    public int heuristicDistance() {
        return heuristicDistance;
    }

    public Set<Node> neighbours() {
        return neighbours;
    }

    public Coordinate coordinate() {
        return coordinate;
    }

    public Node previous() {
        return previous;
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
