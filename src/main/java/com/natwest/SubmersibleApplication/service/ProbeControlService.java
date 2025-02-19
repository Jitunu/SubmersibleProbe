package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class ProbeControlService {
    @Getter
    private Position position;
    @Getter
    private Direction direction;
    private final int gridWidth;
    private final int gridHeight;

    @Getter
    private List<Position> visitedPositions;

    public ProbeControlService(int x, int y, Direction direction, int gridWidth, int gridHeight) {
        this.position = new Position(x, y);
        this.direction = direction;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.visitedPositions = new ArrayList<>();
        this.visitedPositions.add(new Position(x, y));
    }

    public void moveForward() {
        int newX = position.getX();
        int newY = position.getY();

        switch (direction) {
            case NORTH -> newY++;
            case EAST -> newX++;
            case SOUTH -> newY--;
            case WEST -> newX--;
        }

        if (isValidPosition(newX, newY)) {
            position.setX(newX);
            position.setY(newY);
            visitedPositions.add(new Position(newX, newY));
        } else {
            throw new ProbeException("Probe cannot move outside the grid.");
        }
    }

    public void moveBackward() {
        int newX = position.getX();
        int newY = position.getY();

        switch (direction) {
            case NORTH -> newY--;
            case EAST -> newX--;
            case SOUTH -> newY++;
            case WEST -> newX++;
        }

        if (isValidPosition(newX, newY)) {
            position.setX(newX);
            position.setY(newY);
            visitedPositions.add(new Position(newX, newY));
        } else {
            throw new ProbeException("Probe cannot move outside the grid.");
        }
    }

    public void turnLeft() {
        direction = switch (direction) {
            case NORTH -> Direction.WEST;
            case EAST -> Direction.NORTH;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
        };
    }

    public void turnRight() {
        direction = switch (direction) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < gridWidth && y >= 0 && y < gridHeight;
    }

    public String getSummary() {
        return "Probe is at " + position + " facing " + direction + ". Visited positions: " + visitedPositions;
    }
}
