package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.model.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private ProbeControl probeControl;

    public void initializeProbe(int x, int y, Direction direction, int gridWidth, int gridHeight) {
        probeControl = new ProbeControl(x, y, direction, gridWidth, gridHeight);
    }
    public ProbeService() {
        probeControl = new ProbeControl(0, 0, Direction.NORTH, 10, 10);
    }

    public void executeCommands(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F' -> probeControl.moveForward();
                case 'B' -> probeControl.moveBackward();
                case 'L' -> probeControl.turnLeft();
                case 'R' -> probeControl.turnRight();
                default -> throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
    }

    public String getProbeSummary() {
        if (probeControl == null) {
            throw new IllegalStateException("Probe has not been initialized.");
        }
        return probeControl.getSummary();
    }
}
