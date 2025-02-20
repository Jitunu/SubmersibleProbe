package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for controlling the probe's movements and tracking its state.
 * This class handles the probe's position, direction, and obstacle detection.
 */
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

    /**
     * Constructs a new ProbeControl instance with the specified parameters.
     *
     * @param x          The initial X-coordinate of the probe.
     * @param y          The initial Y-coordinate of the probe.
     * @param direction  The initial direction the probe is facing.
     * @param gridWidth  The width of the grid.
     * @param gridHeight The height of the grid.
     * @param obstacles  A list of positions representing obstacles on the grid.
     */
    public ProbeControl(int x, int y, Direction direction, int gridWidth, int gridHeight, List<Position> obstacles) {
        this.position = new Position(x, y);
        this.direction = direction;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.visitedPositions = new ArrayList<>();
        this.visitedPositions.add(new Position(x, y));
        this.obstacles = obstacles;
    }

    /**
     * Moves the probe forward in its current direction.
     *
     * @throws ProbeException If the probe attempts to move outside the grid or into an obstacle.
     */
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

    /**
     * Moves the probe backward in its current direction.
     *
     * @throws ProbeException If the probe attempts to move outside the grid or into an obstacle.
     */
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

    /**
     * Turns the probe left.
     */
    public void turnLeft() {
        direction = switch (direction) {
            case NORTH -> Direction.WEST;
            case EAST -> Direction.NORTH;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
        };
        logger.info("Turned Left");
    }

    /**
     * Turns the probe right.
     */
    public void turnRight() {
        direction = switch (direction) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
        logger.info("Turned Right");
    }

    /**
     * Checks if a position is valid within the grid boundaries.
     *
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return True if the position is valid, otherwise throws a ProbeException.
     * @throws ProbeException If the position is outside the grid boundaries.
     */
    private boolean isValidPosition(int x, int y) {
        if (x >= 0 && x < gridWidth && y >= 0 && y < gridHeight) {
           return true;
        } else {
            throw new ProbeException("Probe cannot move outside the grid.");
        }
    }

    /**
     * Checks if a position contains an obstacle.
     *
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @return True if the position contains an obstacle, otherwise throws a ProbeException.
     * @throws ProbeException If the position contains an obstacle.
     */
    private boolean isObstacle(int x, int y) {
        if(obstacles.contains(new Position(x, y))) {
            throw new ProbeException("Probe cannot move as it found Obstacle.");
        } else {
            return false;
        }
    }

    /**
     * Retrieves a summary of the probe's current state.
     *
     * @return A string containing the probe's position, direction, and visited positions.
     */
    public String getSummary() {
        return "Probe is at " + position + " facing " + direction + ". Visited positions: " + visitedPositions;
    }
}
