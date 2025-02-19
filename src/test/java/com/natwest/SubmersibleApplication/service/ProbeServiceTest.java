package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.model.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProbeServiceTest {
    private ProbeService probeService;

    @BeforeEach
    void setUp() {
        probeService = new ProbeService();
        probeService.initializeProbe(2, 3, Direction.NORTH, 10, 10);
    }

    @Test
    void testMoveForward() {
        probeService.executeCommands("F");
        assertTrue(probeService.getProbeSummary().contains("Position{x=2, y=4}"));
    }

    @Test
    void testMoveBackward() {
        probeService.executeCommands("B");
        assertTrue(probeService.getProbeSummary().contains("Position{x=2, y=2}"));
    }

    @Test
    void testTurnRight() {
        probeService.executeCommands("R");
        assertTrue(probeService.getProbeSummary().contains("EAST"));
    }



    @Test
    void testTurnLeft() {
        probeService.executeCommands("L");
        assertTrue(probeService.getProbeSummary().contains("WEST"));
    }
}
