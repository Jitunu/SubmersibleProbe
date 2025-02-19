package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Data

public class ProbeControlTest {

    private ProbeControl probeControl;

    @BeforeEach
    void setUp() {
        probeControl = new ProbeControl(0,0, Direction.NORTH, 10, 10);
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
        probeControl = new ProbeControl(2,9, Direction.NORTH, 10, 10);
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
        probeControl = new ProbeControl(0,9, Direction.NORTH, 10, 10);
        assertThrows(ProbeException.class, () -> probeControl.moveForward());
    }

    @Test
    void testMoveBackwardOutOfGrid() {
        probeControl = new ProbeControl(0,0, Direction.NORTH, 10, 10);
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
}
