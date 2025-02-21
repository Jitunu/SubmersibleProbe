package com.natwest.SubmersibleApplication.controller;

import com.natwest.SubmersibleApplication.dto.ProbeResponse;
import com.natwest.SubmersibleApplication.model.Grid;
import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.service.IProbeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing the probe's operations.
 * This class exposes endpoints to initialize the probe, execute commands, and retrieve the probe's summary.
 */
@RestController
@RequestMapping("/api/v1")
public class ProbeController {

    private final IProbeService probeService;

    public ProbeController(IProbeService probeService) { this.probeService = probeService; }
    /**
     * Initializes the probe with the given parameters.
     *
     * @param request The request containing probe's initial state and grid configuration.
     * @return A ResponseEntity containing the probe's initial status.
     */
    @PostMapping("/initialize")
    public ResponseEntity<ProbeResponse> initializeProbe(@RequestBody ProbeRequest request) {
        ProbeResponse response = probeService.initializeProbe(request);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Executes a sequence of movement commands for the probe.
     * @param commands A string representing commands (F - Forward, B - Backward, L - Left, R - Right).
     * @return A ResponseEntity containing the probe's updated status.
     */
    @PostMapping("/commands")
    public ResponseEntity<ProbeResponse> executeCommands(@RequestParam String commands) {
        return ResponseEntity.ok(probeService.executeCommands(commands));
    }

    /**
     * Retrieves the current summary of the probe's status.
     *
     * @return A ResponseEntity containing the probe's current position and direction.
     */
    @GetMapping("/summary")
    public ResponseEntity<ProbeResponse> getProbeSummary() {
        return ResponseEntity.ok(probeService.getProbeSummary());
    }

}