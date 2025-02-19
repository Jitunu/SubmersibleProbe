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

public class ProbeControlServiceTest {

    private ProbeControlService probeControlService;

    @BeforeEach
    void setUp() {
        probeControlService = new ProbeControlService(0,0, Direction.NORTH, 10, 10);
    }

    @Test
    void testInitialPosition() {
        assertEquals(0, probeControlService.getPosition().getX());
        assertEquals(0, probeControlService.getPosition().getY());
        assertEquals(Direction.NORTH, probeControlService.getDirection());
    }

    @Test
    void testMoveForward() {
        probeControlService.moveForward();
        assertEquals(0, probeControlService.getPosition().getX());
        assertEquals(1, probeControlService.getPosition().getY());
    }

    @Test
    void testMoveBackward() {
        probeControlService.moveBackward();
        assertEquals(0, probeControlService.getPosition().getX());
        assertEquals(-1, probeControlService.getPosition().getY());
    }

    @Test
    void testTurnLeft() {
        probeControlService.turnLeft();
        assertEquals(Direction.WEST, probeControlService.getDirection());
    }

    @Test
    void testTurnRight() {
        probeControlService.turnRight();
        assertEquals(Direction.EAST, probeControlService.getDirection());
    }

    @Test
    void testMoveForwardOutOfGrid() {
        probeControlService = new ProbeControlService(0,9, Direction.NORTH, 10, 10);
        assertThrows(ProbeException.class, () -> probeControlService.moveForward());
    }

    @Test
    void testMoveBackwardOutOfGrid() {
        probeControlService = new ProbeControlService(0,0, Direction.NORTH, 10, 10);
        assertThrows(ProbeException.class, () -> probeControlService.moveBackward());
    }


    @Test
    void testVisitedPosition() {
        probeControlService.moveForward();
        probeControlService.turnRight();
        probeControlService.moveForward();
        probeControlService.turnLeft();
        probeControlService.moveBackward();

        List<Position> visitedPositions = probeControlService.getVisitedPositions();
        assertEquals(5, visitedPositions.size());
        assertEquals(new Position(0, 0), visitedPositions.get(0));
        assertEquals(new Position(0, 1), visitedPositions.get(1));
        assertEquals(new Position(1, 1), visitedPositions.get(2));
        assertEquals(new Position(1, 0), visitedPositions.get(3));
        assertEquals(new Position(1, -1), visitedPositions.get(4));

    }
}
