package com.messaging.simulation.application.view;

import com.messaging.simulation.domain.MessageSimulation;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageSimulationIdResponseResource {

    private String id;

    public MessageSimulationIdResponseResource(MessageSimulation messageSimulation) {
        this.id = messageSimulation.getId();
    }

    public static MessageSimulationIdResponseResource toResource(MessageSimulation messageSimulation) {
        return new MessageSimulationIdResponseResource(messageSimulation.getId());
    }
}
