package com.natwest.SubmersibleApplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Represents a position on the grid with x and y coordinates.
 * This class is used to track the probe's location and obstacles on the grid.
 */
@Setter
@Getter
@AllArgsConstructor
public class Position {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position position)) return false;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

