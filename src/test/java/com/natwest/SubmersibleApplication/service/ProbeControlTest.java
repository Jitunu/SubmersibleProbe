package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.exception.ProbeException;
import com.natwest.SubmersibleApplication.model.Command;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Grid;
import com.natwest.SubmersibleApplication.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProbeControlTest {

    private ProbeControl probe;
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(10, 10);
        probe = new ProbeControl();  // No constructor args
        ProbeRequest request = new ProbeRequest(2,2, Direction.NORTH, 10, 10 , Set.of(new Position(1,3)));
        probe.initialize(request);
    }

    @Test
    void testMoveForward() {
        probe.processCommands("F");
        assertEquals(2, probe.getPosition().getX());
        assertEquals(3, probe.getPosition().getY());
    }

    @Test
    void testMoveBackward() {
        probe.processCommands("B");
        assertEquals(2, probe.getPosition().getX());
        assertEquals(1, probe.getPosition().getY());
    }

    @Test
    void testTurnLeft() {
        probe.processCommands("L");
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void testTurnRight() {
        probe.processCommands("R");
        assertEquals(Direction.EAST, probe.getDirection());
    }

//    @Test
//    void testVisitedPosition() {
//        probe.moveForward();
//        probe.turnRight();
//        probe.moveForward();
//        probe.turnLeft();
//        probe.moveBackward();
//
//        List<Position> visitedPositions = probe.getVisitedPositions();
//        assertEquals(4, visitedPositions.size());
//        assertEquals(new Position(0, 0), visitedPositions.get(0));
//        assertEquals(new Position(0, 1), visitedPositions.get(1));
//        assertEquals(new Position(1, 1), visitedPositions.get(2));
//        assertEquals(new Position(1, 0), visitedPositions.get(3));
//
//    }

    @Test
    void testMoveForwardWithObstacle() {
        ProbeRequest request = new ProbeRequest(4, 4, Direction.NORTH, 5, 5, Set.of(new Position(4, 5)));
        probe.initialize(request);
        assertThrows(ProbeException.class, () -> probe.processCommands("F"));
    }

    @Test
    void testMoveOutOfBounds() {
        ProbeRequest request = new ProbeRequest(4, 4, Direction.NORTH, 5, 5, null);
        probe.initialize(request);
        assertThrows(ProbeException.class, () -> probe.processCommands("F"));
    }
}
