package com.natwest.SubmersibleApplication.controller;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.model.Position;
import com.natwest.SubmersibleApplication.model.ProbeRequest;
import com.natwest.SubmersibleApplication.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProbeController {
    @Autowired
    private ProbeService probeService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeProbe(@RequestBody ProbeRequest probeRequest) {
        probeService.initializeProbe(probeRequest.getX(), probeRequest.getY(), probeRequest.getDirection(),
                probeRequest.getGridWidth(), probeRequest.getGridHeight(), probeRequest.getObstacles());
        return new ResponseEntity<>("Probe initialized successfully.", HttpStatus.OK);
    }

    @PostMapping("/commands")
    public ResponseEntity<String> executeCommands(@RequestParam String commands) {
        probeService.executeCommands(commands);
        return new ResponseEntity<>("Commands executed successfully.", HttpStatus.OK);
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getProbeSummary() {
        return new ResponseEntity<>(probeService.getProbeSummary(), HttpStatus.OK);
    }

}