package com.natwest.SubmersibleApplication.controller;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.ProbeRequest;
import com.natwest.SubmersibleApplication.service.ProbeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProbeControllerTest {

    @Mock
    private ProbeService probeService;

    @InjectMocks
    private ProbeController probeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitializeProbe() {

        int x = 0;
        int y = 0;
        Direction direction = Direction.NORTH;
        int gridWidth = 10;
        int gridHeight = 10;

        ProbeRequest probeRequest = new ProbeRequest();
        probeRequest.setX(x);
        probeRequest.setY(y);
        probeRequest.setDirection(direction);
        probeRequest.setGridWidth(gridWidth);
        probeRequest.setGridHeight(gridHeight);
        probeRequest.setObstacles(new ArrayList<>());

        ResponseEntity<String> response = probeController.initializeProbe(probeRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Probe initialized successfully.", response.getBody());
        verify(probeService, times(1)).initializeProbe(x, y, direction, gridWidth, gridHeight, new ArrayList<>());
    }

    @Test
    void testExecuteCommands() {

        String commands = "FFRFF";

        ResponseEntity<String> response = probeController.executeCommands(commands);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Commands executed successfully.", response.getBody());
        verify(probeService, times(1)).executeCommands(commands);
    }

    @Test
    void testGetProbeSummary() {

        String expectedSummary = "Probe is at (2, 3) facing EAST. Visited positions: [(0, 0), (0, 1), (1, 1), (2, 1), (2, 2), (2, 3)]";
        when(probeService.getProbeSummary()).thenReturn(expectedSummary);

        ResponseEntity<String> response = probeController.getProbeSummary();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedSummary, response.getBody());
        verify(probeService, times(1)).getProbeSummary();
    }

    @Test
    void testExecuteCommandsWithInvalidCommand() {

        String commands = "FXZ"; // Invalid command 'X' and 'Z'
        doThrow(new IllegalArgumentException("Invalid command: X")).when(probeService).executeCommands(commands);

        assertThrows(IllegalArgumentException.class,() -> probeController.executeCommands(commands));
        verify(probeService, times(1)).executeCommands(commands);
    }

    @Test
    void testGetProbeSummaryWhenProbeNotInitialized() {

        when(probeService.getProbeSummary()).thenThrow(new IllegalStateException("Probe has not been initialized."));

        assertThrows(IllegalStateException.class,() -> probeController.getProbeSummary());
        verify(probeService, times(1)).getProbeSummary();
    }
}
