package com.natwest.SubmersibleApplication.controller;

import com.natwest.SubmersibleApplication.model.ProbeRequest;
import com.natwest.SubmersibleApplication.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing the probe's operations.
 * This class exposes endpoints to initialize the probe, execute commands, and retrieve the probe's summary.
 */
@RestController
public class ProbeController {
    @Autowired
    private ProbeService probeService;

    /**
     * Initializes the probe with the given parameters.
     *
     * @param probeRequest The request body containing the probe's initial position, direction, grid dimensions, and obstacles.
     * @return A ResponseEntity with a success message and HTTP status OK.
     */
    @PostMapping("/initialize")
    public ResponseEntity<String> initializeProbe(@RequestBody ProbeRequest probeRequest) {
        probeService.initializeProbe(probeRequest.getX(), probeRequest.getY(), probeRequest.getDirection(),
                probeRequest.getGridWidth(), probeRequest.getGridHeight(), probeRequest.getObstacles());
        return new ResponseEntity<>("Probe initialized successfully.", HttpStatus.OK);
    }

    /**
     * Executes a sequence of movement commands for the probe.
     * @param commands A string representing commands (F - Forward, B - Backward, L - Left, R - Right).
     * @return A ResponseEntity with a success message and HTTP status OK.
     */
    @PostMapping("/commands")
    public ResponseEntity<String> executeCommands(@RequestParam String commands) {
        probeService.executeCommands(commands);
        return new ResponseEntity<>("Commands executed successfully.", HttpStatus.OK);
    }

    /**
     * Retrieves the current summary of the probe's status.
     *
     * @return A ResponseEntity containing the probe's summary (position, direction, and visited positions) and HTTP status OK.
     */
    @GetMapping("/summary")
    public ResponseEntity<String> getProbeSummary() {
        return new ResponseEntity<>(probeService.getProbeSummary(), HttpStatus.OK);
    }

}