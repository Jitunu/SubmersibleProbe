package com.natwest.SubmersibleApplication.model;

public class Grid {
    private final int width, height;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
