package com.messaging.simulation.application.service;

import com.messaging.simulation.domain.MessageSimulation;
import com.messaging.simulation.domain.repository.MessageSimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSimulationService {

    private MessageSimulationRepository messageSimulationRepository;

    @Autowired
    public MessageSimulationService(MessageSimulationRepository messageSimulationRepository) {
        this.messageSimulationRepository = messageSimulationRepository;
    }

    public MessageSimulation create(MessageSimulation messageSimulation) {
        return messageSimulationRepository.save(messageSimulation);
    }
}
