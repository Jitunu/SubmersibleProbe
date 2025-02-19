package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProbeControlTest {

    private ProbeControl probeControl;

    @BeforeEach
    void setUp() {
        probeControl = new ProbeControl(0,0, Direction.NORTH, 10, 10, new ArrayList<>());
    }

    @Test
    void testInitialPosition() {
        assertEquals(0, probeControl.getPosition().getX());
        assertEquals(0, probeControl.getPosition().getY());
        assertEquals(Direction.NORTH, probeControl.getDirection());
    }

    @Test
    void testMoveForward() {
        probeControl.moveForward();
        assertEquals(0, probeControl.getPosition().getX());
        assertEquals(1, probeControl.getPosition().getY());
    }

    @Test
    void testMoveBackward() {
        probeControl = new ProbeControl(2,9, Direction.NORTH, 10, 10, new ArrayList<>());
        probeControl.moveBackward();
        assertEquals(2, probeControl.getPosition().getX());
        assertEquals(8, probeControl.getPosition().getY());
    }

    @Test
    void testTurnLeft() {
        probeControl.turnLeft();
        assertEquals(Direction.WEST, probeControl.getDirection());
    }

    @Test
    void testTurnRight() {
        probeControl.turnRight();
        assertEquals(Direction.EAST, probeControl.getDirection());
    }

    @Test
    void testMoveForwardOutOfGrid() {
        probeControl = new ProbeControl(0,9, Direction.NORTH, 10, 10, new ArrayList<>());
        assertThrows(ProbeException.class, () -> probeControl.moveForward());
    }

    @Test
    void testMoveBackwardOutOfGrid() {
        assertThrows(ProbeException.class, () -> probeControl.moveBackward());
    }


    @Test
    void testVisitedPosition() {
        probeControl.moveForward();
        probeControl.turnRight();
        probeControl.moveForward();
        probeControl.turnLeft();
        probeControl.moveBackward();

        List<Position> visitedPositions = probeControl.getVisitedPositions();
        assertEquals(4, visitedPositions.size());
        assertEquals(new Position(0, 0), visitedPositions.get(0));
        assertEquals(new Position(0, 1), visitedPositions.get(1));
        assertEquals(new Position(1, 1), visitedPositions.get(2));
        assertEquals(new Position(1, 0), visitedPositions.get(3));

    }

    @Test
    void testGetSummary() {
        probeControl.moveForward();
        probeControl.turnRight();
        probeControl.moveForward();

        String summary = probeControl.getSummary();
        assertTrue(summary.contains("Probe is at Position{x=1, y=1} facing EAST"));
        assertTrue(summary.contains("Visited positions: [Position{x=0, y=0}, Position{x=0, y=1}, Position{x=1, y=1}]"));
    }

    @Test
    void testMoveForwardWithObstacle() {
        // Add an obstacle at (0, 1)
        List<Position> obstacles = new ArrayList<>();
        obstacles.add(new Position(0, 1));
        probeControl = new ProbeControl(0, 0, Direction.NORTH, 10, 10, obstacles);

        // Try to move forward into the obstacle
        assertThrows(ProbeException.class, () -> probeControl.moveForward());
    }

    @Test
    void testMoveForwardWithMultipleObstacles() {
        // Add obstacles at (0, 1) and (1, 0)
        List<Position> obstacles = new ArrayList<>();
        obstacles.add(new Position(0, 1));
        obstacles.add(new Position(1, 0));
        probeControl = new ProbeControl(0, 0, Direction.NORTH, 10, 10, obstacles);

        // Try to move forward into the first obstacle
        assertThrows(ProbeException.class, () -> probeControl.moveForward());

        // Turn right and try to move forward into the second obstacle
        probeControl.turnRight();
        assertThrows(ProbeException.class, () -> probeControl.moveForward());
    }
}
