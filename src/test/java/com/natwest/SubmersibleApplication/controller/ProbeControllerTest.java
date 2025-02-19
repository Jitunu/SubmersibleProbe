package com.natwest.SubmersibleApplication.controller;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.service.ProbeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

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
        // Arrange
        int x = 0;
        int y = 0;
        Direction direction = Direction.NORTH;
        int gridWidth = 10;
        int gridHeight = 10;

        // Act
        ResponseEntity<String> response = probeController.initializeProbe(x, y, direction, gridWidth, gridHeight);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Probe initialized successfully.", response.getBody());
        verify(probeService, times(1)).initializeProbe(x, y, direction, gridWidth, gridHeight);
    }

    @Test
    void testExecuteCommands() {
        // Arrange
        String commands = "FFRFF";

        // Act
        ResponseEntity<String> response = probeController.executeCommands(commands);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Commands executed successfully.", response.getBody());
        verify(probeService, times(1)).executeCommands(commands);
    }

    @Test
    void testGetProbeSummary() {
        // Arrange
        String expectedSummary = "Probe is at (2, 3) facing EAST. Visited positions: [(0, 0), (0, 1), (1, 1), (2, 1), (2, 2), (2, 3)]";
        when(probeService.getProbeSummary()).thenReturn(expectedSummary);

        // Act
        ResponseEntity<String> response = probeController.getProbeSummary();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedSummary, response.getBody());
        verify(probeService, times(1)).getProbeSummary();
    }

    @Test
    void testExecuteCommandsWithInvalidCommand() {
        // Arrange
        String commands = "FXZ"; // Invalid command 'X' and 'Z'
        doThrow(new IllegalArgumentException("Invalid command: X")).when(probeService).executeCommands(commands);

        // Assert
        assertThrows(IllegalArgumentException.class,() -> probeController.executeCommands(commands));
        verify(probeService, times(1)).executeCommands(commands);
    }

    @Test
    void testGetProbeSummaryWhenProbeNotInitialized() {
        // Arrange
        when(probeService.getProbeSummary()).thenThrow(new IllegalStateException("Probe has not been initialized."));

        // Assert
        assertThrows(IllegalStateException.class,() -> probeController.getProbeSummary());
        verify(probeService, times(1)).getProbeSummary();
    }
}
