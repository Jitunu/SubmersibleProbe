package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing the probe's operations.
 * This class handles the initialization, command execution, and summary retrieval for the probe.
 */
@Service
public class ProbeService {

    private static final Logger logger = LoggerFactory.getLogger(ProbeService.class);
    private ProbeControl probeControl;

    /**
     * Initializes the probe with the specified parameters.
     *
     * @param x          The initial X-coordinate of the probe.
     * @param y          The initial Y-coordinate of the probe.
     * @param direction  The initial direction the probe is facing.
     * @param gridWidth  The width of the grid.
     * @param gridHeight The height of the grid.
     * @param obstacles  A list of positions representing obstacles on the grid.
     */
    public void initializeProbe(int x, int y, Direction direction, int gridWidth, int gridHeight, List<Position> obstacles) {
        logger.info("Initializing probe at ({}, {}) facing {} on a {}x{} grid with obstacles: {}", x, y, direction, gridWidth, gridHeight, obstacles);
        probeControl = new ProbeControl(x, y, direction, gridWidth, gridHeight, obstacles);
    }

    /**
     * Default constructor initializes the probe with default values.
     */
    public ProbeService() {
        probeControl = new ProbeControl(0, 0, Direction.NORTH, 10, 10, new ArrayList<>());
    }

    /**
     * Executes a given sequence of movement commands.
     * @param commands String of commands ('F' for forward, 'B' for backward, 'L' for left, 'R' for right).
     * @throws IllegalStateException If the probe has not been initialized.
     * @throws IllegalArgumentException If an invalid command is encountered.
     */
    public void executeCommands(String commands) {
        if (probeControl == null) {
            logger.error("Probe has not been initialized.");
            throw new IllegalStateException("Probe has not been initialized.");
        }
        logger.info("Executing commands: {}", commands);
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F' -> probeControl.moveForward();
                case 'B' -> probeControl.moveBackward();
                case 'L' -> probeControl.turnLeft();
                case 'R' -> probeControl.turnRight();
                default -> throw new IllegalArgumentException("Invalid command: " + command);
            }
            logger.info(getProbeSummary());
        }
    }

    /**
     * Retrieves the current summary of the probe's status.
     *
     * @return A string containing the probe's position, direction, and visited positions.
     * @throws IllegalStateException If the probe has not been initialized.
     */
    public String getProbeSummary() {
        if (probeControl == null) {
            logger.error("Probe has not been initialized.");
            throw new IllegalStateException("Probe has not been initialized.");
        }
        return probeControl.getSummary();
    }
}
