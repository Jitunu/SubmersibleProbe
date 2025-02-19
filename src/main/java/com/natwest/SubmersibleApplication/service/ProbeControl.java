package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProbeControl {

    private static final Logger logger = LoggerFactory.getLogger(ProbeControl.class);

    @Getter
    private Position position;
    @Getter
    private Direction direction;
    private final int gridWidth;
    private final int gridHeight;

    @Getter
    private List<Position> visitedPositions;
    private List<Position> obstacles;

    public ProbeControl(int x, int y, Direction direction, int gridWidth, int gridHeight, List<Position> obstacles) {
        this.position = new Position(x, y);
        this.direction = direction;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.visitedPositions = new ArrayList<>();
        this.visitedPositions.add(new Position(x, y));
        this.obstacles = obstacles;
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

        if (isValidPosition(newX, newY)  && !isObstacle(newX, newY)) {
            position.setX(newX);
            position.setY(newY);
            visitedPositions.add(new Position(newX, newY));
            logger.info("Moved Forward!!!");
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

        if (isValidPosition(newX, newY)  && !isObstacle(newX, newY)) {
            position.setX(newX);
            position.setY(newY);
            visitedPositions.add(new Position(newX, newY));
            logger.info("Moved Backward!!!");
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
        logger.info("Turned Left");
    }

    public void turnRight() {
        direction = switch (direction) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
        logger.info("Turned Right");
    }

    private boolean isValidPosition(int x, int y) {
        if (x >= 0 && x < gridWidth && y >= 0 && y < gridHeight) {
           return true;
        } else {
            throw new ProbeException("Probe cannot move outside the grid.");
        }
    }

    private boolean isObstacle(int x, int y) {
        if(obstacles.contains(new Position(x, y))) {
            throw new ProbeException("Probe cannot move as it found Obstacle.");
        } else {
            return false;
        }
    }

    public String getSummary() {
        return "Probe is at " + position + " facing " + direction + ". Visited positions: " + visitedPositions;
    }
}
