package com.messaging.simulation.application.view;

import com.messaging.simulation.domain.MessageSimulation;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessageSimulationResponseResource {

    private String username;

    private String text;

    private String expiration_date;

    public MessageSimulationResponseResource(MessageSimulation messageSimulation) {
        this.username = messageSimulation.getUsername();
        this.text = messageSimulation.getText();
        this.expiration_date = messageSimulation.getExpiration_date();
    }

    public static MessageSimulationResponseResource toResource(MessageSimulation messageSimulation) {
        if (messageSimulation == null) {
            return null;
        }
        return new MessageSimulationResponseResource(messageSimulation);
    }
}
