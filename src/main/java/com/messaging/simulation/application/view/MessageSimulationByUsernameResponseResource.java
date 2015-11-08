package com.messaging.simulation.application.view;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.messaging.simulation.domain.MessageSimulation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Data
public class MessageSimulationByUsernameResponseResource {

    private String id;

    private String text;

    public MessageSimulationByUsernameResponseResource(MessageSimulation messageSimulation) {
        this.id = messageSimulation.getId();
        this.text = messageSimulation.getText();
    }

    public static List<MessageSimulationByUsernameResponseResource> toResource(List<MessageSimulation> messageSimulationList) {
        if (messageSimulationList.isEmpty() || messageSimulationList == null) {
            return Collections.emptyList();
        }

        List<MessageSimulationByUsernameResponseResource> results = FluentIterable.from(messageSimulationList).transform(new Function<MessageSimulation, MessageSimulationByUsernameResponseResource>() {
            @Override
            public MessageSimulationByUsernameResponseResource apply(MessageSimulation input) {
                assert input != null;
                return new MessageSimulationByUsernameResponseResource(input.getId(), input.getText());
            }
        }).toList();

        return results;
    }
}
