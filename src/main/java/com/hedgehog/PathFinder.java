package com.hedgehog;

import com.hedgehog.pojo.Node;

public interface PathFinder<T extends Node> {
    T findBestPath(T current, T end);
}
