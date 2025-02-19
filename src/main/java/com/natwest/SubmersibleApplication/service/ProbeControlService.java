package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProbeControlService {
    private Position position;
    private Direction direction;
    private final int gridWidth;
    private final int gridHeight;

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

    }

    public void moveBackward() {

    }

    public void turnLeft() {

    }

    public void turnRight() {

    }

    private boolean isValidPosition(int x, int y) {
        return false;
    }

}
