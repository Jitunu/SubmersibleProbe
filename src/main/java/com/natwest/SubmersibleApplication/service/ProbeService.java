package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.model.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private ProbeControlService probeControlService;

    public void initializeProbe(int x, int y, Direction direction, int gridWidth, int gridHeight) {
        probeControlService = new ProbeControlService(x, y, direction, gridWidth, gridHeight);
    }

    public void executeCommands(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F' -> probeControlService.moveForward();
                case 'B' -> probeControlService.moveBackward();
                case 'L' -> probeControlService.turnLeft();
                case 'R' -> probeControlService.turnRight();
                default -> throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
    }

    public String getProbeSummary() {
        if (probeControlService == null) {
            throw new IllegalStateException("Probe has not been initialized.");
        }
        return probeControlService.getSummary();
    }
}
