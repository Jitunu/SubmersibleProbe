package com.natwest.SubmersibleApplication.model;

import java.util.List;

public class ProbeRequest {
    private int x;
    private int y;
    private Direction direction;
    private int gridWidth;
    private int gridHeight;
    private List<Position> obstacles;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public List<Position> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Position> obstacles) {
        this.obstacles = obstacles;
    }
}