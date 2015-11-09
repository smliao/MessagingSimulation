package com.messaging.simulation.application.service;

import com.messaging.simulation.domain.ExpiredMessageSimulation;
import com.messaging.simulation.domain.MessageSimulation;
import com.messaging.simulation.domain.repository.ExpiredMessageSimulationRepository;
import com.messaging.simulation.domain.repository.MessageSimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageSimulationService {

    private MessageSimulationRepository messageSimulationRepository;

    private ExpiredMessageSimulationRepository expiredMessageSimulationRepository;
    private static final String CURRENT_TIME = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));;

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

        if(messageSimulation.getExpiration_date().compareTo(CURRENT_TIME) < 0){
            messageSimulationRepository.delete(id);
            expiredMessageSimulationRepository.save(ExpiredMessageSimulation.from(messageSimulation));
        }

        return messageSimulation;
    }

    public void deleteAll(){
        messageSimulationRepository.deleteAll();
    }

    public List<MessageSimulation> findByUsername(String username) {
        List<MessageSimulation> returnedMessages = messageSimulationRepository.deleteByUsername(username);

        for (MessageSimulation returnedMessage : returnedMessages) {
            expiredMessageSimulationRepository.save(ExpiredMessageSimulation.from(returnedMessage));
        }

        List<MessageSimulation> results = filterUnexpiredMessages(returnedMessages);

        return results;
    }

    private List<MessageSimulation> filterUnexpiredMessages(List<MessageSimulation> returnedMessages) {
        List<MessageSimulation> results = new ArrayList<>();

        for (MessageSimulation returnedMessage : returnedMessages) {
            if (returnedMessage.getExpiration_date()
                    .compareTo(CURRENT_TIME) > 0) {
                    results.add(returnedMessage);
            }
        }
        return results;
    }
}
