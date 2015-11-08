package com.messaging.simulation.application.view;

import com.messaging.simulation.domain.MessageSimulation;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MessageSimulationResponseResource {

    private String username;

    private String text;

    private String expiration_date;

    public MessageSimulationResponseResource(MessageSimulation messageSimulation) {
        this.username = messageSimulation.getUsername();
        this.text = messageSimulation.getText();
        this.expiration_date = messageSimulation.getText();
    }

    public static Map<String, Object> toCreateResource(MessageSimulation messageSimulation) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", messageSimulation.getId());
        return result;
    }

    public static MessageSimulationResponseResource toResource(MessageSimulation messageSimulation) {
        return new MessageSimulationResponseResource(messageSimulation);
    }
}
