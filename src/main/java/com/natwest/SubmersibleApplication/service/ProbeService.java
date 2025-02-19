package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProbeService {

    private static final Logger logger = LoggerFactory.getLogger(ProbeService.class);
    private ProbeControl probeControl;

    public void initializeProbe(int x, int y, Direction direction, int gridWidth, int gridHeight, List<Position> obstacles) {
        logger.info("Initializing probe at ({}, {}) facing {} on a {}x{} grid with obstacles: {}", x, y, direction, gridWidth, gridHeight, obstacles);
        probeControl = new ProbeControl(x, y, direction, gridWidth, gridHeight, obstacles);
    }
    public ProbeService() {
        probeControl = new ProbeControl(0, 0, Direction.NORTH, 10, 10, new ArrayList<>());
    }

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

    public String getProbeSummary() {
        if (probeControl == null) {
            logger.error("Probe has not been initialized.");
            throw new IllegalStateException("Probe has not been initialized.");
        }
        return probeControl.getSummary();
    }
}
