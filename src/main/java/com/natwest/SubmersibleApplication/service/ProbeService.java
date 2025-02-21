package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.dto.ProbeResponse;
import com.natwest.SubmersibleApplication.exception.ProbeNotInitializedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for managing the probe's operations.
 * This class handles the initialization, command execution, and summary retrieval for the probe.
 */
@Service
public class ProbeService implements IProbeService{

    private static final Logger logger = LoggerFactory.getLogger(ProbeService.class);
    private final ProbeControl probeControl;
    private boolean isInitialized = false;

    public ProbeService(ProbeControl probeControl) {
        this.probeControl = probeControl;
    }

    /**
     * Initializes the probe with the given request parameters.
     *
     * @param probeRequest The request containing probe's initial state and grid configuration.
     * @return The initial probe status.
     */
    public ProbeResponse initializeProbe(ProbeRequest probeRequest) {
        isInitialized = true;
        probeControl.initialize(probeRequest);
        logger.info("Probe Initialize : {} ", probeRequest);
        return getProbeSummary();
    }

    /**
     * Executes a sequence of movement commands for the probe.
     *
     * @param commands A string representing movement commands (F - Forward, B - Backward, L - Left, R - Right).
     * @return The updated probe status.
     */
    public ProbeResponse executeCommands(String commands) {
        if(isInitialized) {
            probeControl.processCommands(commands);
            logger.info("Execute commands: {}", commands);
        } else {
            throw new ProbeNotInitializedException("Please Initialize Probe first!");
        }
        return getProbeSummary();
    }

    /**
     * Retrieves the current summary of the probe's status.
     *
     * @return The probe's current position and direction.
     */
    public ProbeResponse getProbeSummary() {
        return new ProbeResponse(probeControl.getPosition().getX(),
                                probeControl.getPosition().getY(),
                                probeControl.getDirection());
    }
}
