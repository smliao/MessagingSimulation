package com.messaging.simulation.application.service;

import com.messaging.simulation.domain.ExpiredMessageSimulation;
import com.messaging.simulation.domain.MessageSimulation;
import com.messaging.simulation.domain.repository.ExpiredMessageSimulationRepository;
import com.messaging.simulation.domain.repository.MessageSimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageSimulationService {

    private MessageSimulationRepository messageSimulationRepository;

    private ExpiredMessageSimulationRepository expiredMessageSimulationRepository;

    @Autowired
    public MessageSimulationService(MessageSimulationRepository messageSimulationRepository, ExpiredMessageSimulationRepository expiredMessageSimulationRepository) {
        this.messageSimulationRepository = messageSimulationRepository;
        this.expiredMessageSimulationRepository = expiredMessageSimulationRepository;
    }

    public MessageSimulation create(MessageSimulation messageSimulation) {

        return messageSimulationRepository.save(messageSimulation);
    }

    public MessageSimulation findById(String id) {
        MessageSimulation messageSimulation = messageSimulationRepository.findOne(id);
        if (messageSimulation == null) {
            ExpiredMessageSimulation expiredMessageSimulation = expiredMessageSimulationRepository.findOne(id);
            return MessageSimulation.from(expiredMessageSimulation);
        }
        return messageSimulation;
    }

    public List<MessageSimulation> findByUsername(String username) {
        List<MessageSimulation> returnedMessages = messageSimulationRepository.deleteByUsername(username);

        for (MessageSimulation returnedMessage : returnedMessages) {
            expiredMessageSimulationRepository.save(ExpiredMessageSimulation.from(returnedMessage));
        }

        return returnedMessages;
    }
}
