package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.exception.InvalidCommandException;
import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Command;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Grid;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Component responsible for controlling the probe's movement and tracking its state.
 * It manages probe positioning, direction, and execution of movement commands.
 */
@Component
public class ProbeControl {

    private static final Logger logger = LoggerFactory.getLogger(ProbeControl.class);

    private Position position;
    private Direction direction;
    private Grid grid;
    private Set<Position> obstacles;

    public Position getPosition() {
        return new Position(position.getX(), position.getY()); // Defensive Copy
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Initializes the probe's position, direction, and grid size.
     *
     * @param probeRequest The request containing probe's initial state and grid dimensions.
     */
    public void initialize(ProbeRequest probeRequest) {
        this.position = new Position(probeRequest.getX(), probeRequest.getY());
        this.direction = probeRequest.getDirection();
        this.grid = new Grid(probeRequest.getGridWidth(), probeRequest.getGridHeight());
        this.obstacles = probeRequest.getObstacles();
    }

    /**
     * Processes a sequence of movement commands for the probe.
     *
     * @param commands A string representing movement commands (F - Forward, B - Backward, L - Left, R - Right).
     */
    public void processCommands(String commands) {
        for (char command : commands.toCharArray()) {
            move(Command.valueOf(String.valueOf(command)));
        }
    }

    /**
     * Moves the probe based on the given command.
     *
     * @param command The movement command.
     */
    public void move(Command command) {
        switch (command) {
            case F -> moveForward();
            case B -> moveBackward();
            case L -> turnLeft();
            case R -> turnRight();
            default -> throw new InvalidCommandException("Invalid command");
        }
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

        validateAndMove(newX, newY);
        logger.info("Moved Forward!!!");
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

        validateAndMove(newX, newY);
        logger.info("Moved Backward!!!");
    }


    /**
     * Validate probe movement is within the grid and obstacles and then move.
     * @param newX The X-coordinate to check.
     * @param newY The Y-coordinate to check.
     * @throws ProbeException If the probe attempts to move outside the grid or into an obstacle.
     */
    private void validateAndMove(int newX, int newY) {
        if (!grid.isWithinBounds(newX, newY)) {
            throw new ProbeException("Movement out of bounds");
        }
        if (obstacles.contains(new Position(newX, newY))) {
            throw new ProbeException("Obstacle at (" + newX + ", " + newY + ")");
        }
        position.setX(newX);
        position.setY(newY);
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
}
