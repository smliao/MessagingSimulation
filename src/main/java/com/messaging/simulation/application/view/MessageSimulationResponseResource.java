package com.messaging.simulation.application.view;

import com.messaging.simulation.domain.MessageSimulation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String, Object> toCreateResource(MessageSimulation messageSimulation) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", messageSimulation.getId());
        return result;
    }

    public static MessageSimulationResponseResource toResource(MessageSimulation messageSimulation) {
        return new MessageSimulationResponseResource(messageSimulation);
    }

    public static List<Map<String, Object>> toListResource(List<MessageSimulation> messageSimulationList) {

        List<Map<String, Object>> results = new ArrayList<>();

        if (messageSimulationList.isEmpty() || messageSimulationList == null) {
            return Collections.emptyList();
        }

        for (MessageSimulation messageSimulation : messageSimulationList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", messageSimulation.getId());
            map.put("text", messageSimulation.getText());

            results.add(map);
        }

        return results;
    }
}
