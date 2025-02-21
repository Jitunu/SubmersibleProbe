package com.natwest.SubmersibleApplication.service;

import com.natwest.SubmersibleApplication.dto.ProbeRequest;
import com.natwest.SubmersibleApplication.dto.ProbeResponse;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing the probe's operations.
 * This interface handles the initialization, command execution, and summary retrieval for the probe.
 */
@Service
public interface IProbeService {

    /**
     * Initializes the probe with the given request parameters.
     *
     * @param probeRequest The request containing probe's initial position, direction, and grid configuration.
     * @return A {@link ProbeResponse} containing the probe's initial state.
     */
    public ProbeResponse initializeProbe(ProbeRequest probeRequest);

    /**
     * Executes a sequence of movement commands for the probe.
     * The commands are represented as a string where:
     * - 'F' moves the probe forward.
     * - 'B' moves the probe backward.
     * - 'L' turns the probe left.
     * - 'R' turns the probe right.
     *
     * @param commands A string representing the movement commands.
     * @return A {@link ProbeResponse} containing the updated probe status after command execution.
     */
    public ProbeResponse executeCommands(String commands);

    /**
     * Retrieves the current position and direction of the probe.
     *
     * @return A {@link ProbeResponse} containing the probe's latest status.
     */
    public ProbeResponse getProbeSummary();
}
