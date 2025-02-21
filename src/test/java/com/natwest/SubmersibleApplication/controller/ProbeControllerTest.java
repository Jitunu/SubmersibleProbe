package com.natwest.SubmersibleApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.dto.ProbeResponse;
import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.service.IProbeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProbeController.class)
public class ProbeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProbeService probeService;

    private ProbeRequest probeRequest;
    private ProbeResponse probeResponse;

    @BeforeEach
    void setUp() {
        probeRequest = new ProbeRequest(2, 2, Direction.EAST, 5, 5, null);
        probeResponse = new ProbeResponse(0, 0, Direction.NORTH);
    }

    @Test
    void testInitializeProbe() throws Exception {
        when(probeService.initializeProbe(Mockito.any(ProbeRequest.class))).thenReturn(probeResponse);

        mockMvc.perform(post("/api/v1/initialize")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(probeRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.x").value(0))
                .andExpect(jsonPath("$.y").value(0))
                .andExpect(jsonPath("$.direction").value("NORTH"));
    }

    @Test
    void testExecuteCommands() throws Exception {
        ProbeResponse probeResponse = new ProbeResponse(2, 2, Direction.EAST);
        when(probeService.executeCommands("FFR")).thenReturn(probeResponse);

        mockMvc.perform(post("/api/v1/commands")
                        .param("commands", "FFR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x").value(2))
                .andExpect(jsonPath("$.y").value(2))
                .andExpect(jsonPath("$.direction").value("EAST"));
    }

    @Test
    void testGetProbeSummary() throws Exception {
        ProbeResponse probeResponse = new ProbeResponse(1, 1, Direction.SOUTH);
        when(probeService.getProbeSummary()).thenReturn(probeResponse);

        mockMvc.perform(get("/api/v1/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x").value(1))
                .andExpect(jsonPath("$.y").value(1))
                .andExpect(jsonPath("$.direction").value("SOUTH"));
    }
}
