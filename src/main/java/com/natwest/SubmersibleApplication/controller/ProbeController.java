package com.natwest.SubmersibleApplication.controller;

import com.natwest.SubmersibleApplication.model.Direction;
import com.natwest.SubmersibleApplication.service.ProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProbeController {
    @Autowired
    private ProbeService probeService;

    @PostMapping("/initialize")
    public ResponseEntity<String> initializeProbe(@RequestParam int x, @RequestParam int y, @RequestParam Direction direction,
                                                 @RequestParam int gridWidth, @RequestParam int gridHeight) {
        probeService.initializeProbe(x, y, direction, gridWidth, gridHeight);
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