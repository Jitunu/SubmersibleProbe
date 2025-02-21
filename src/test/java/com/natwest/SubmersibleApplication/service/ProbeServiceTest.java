package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.dto.ProbeResponse;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Grid;
import com.natwest.SubmersibleApplication.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProbeServiceTest {

    @InjectMocks
    private ProbeService probeService;

    @Mock
    private ProbeControl probeControl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitializeProbe() {
        ProbeRequest request = new ProbeRequest(0, 0, Direction.NORTH, 5, 5, null);
        when(probeControl.getPosition()).thenReturn(new Position(0, 0));
        when(probeControl.getDirection()).thenReturn(Direction.NORTH);

        ProbeResponse response = probeService.initializeProbe(request);

        assertNotNull(response);
        assertEquals(0, response.getX());
        assertEquals(0, response.getY());
        assertEquals(Direction.NORTH, response.getDirection());

        verify(probeControl, times(1)).initialize(request);
    }

    @Test
    void testExecuteCommands() {
        when(probeControl.getPosition()).thenReturn(new Position(2, 2));
        when(probeControl.getDirection()).thenReturn(Direction.EAST);
        probeService.initializeProbe(null);
        ProbeResponse response = probeService.executeCommands("FFR");

        assertNotNull(response);
        assertEquals(2, response.getX());
        assertEquals(2, response.getY());
        assertEquals(Direction.EAST, response.getDirection());

        verify(probeControl, times(1)).processCommands("FFR");
    }

    @Test
    void testGetProbeSummary() {
        when(probeControl.getPosition()).thenReturn(new Position(1, 1));
        when(probeControl.getDirection()).thenReturn(Direction.SOUTH);

        ProbeResponse response = probeService.getProbeSummary();

        assertNotNull(response);
        assertEquals(1, response.getX());
        assertEquals(1, response.getY());
        assertEquals(Direction.SOUTH, response.getDirection());
    }
}
