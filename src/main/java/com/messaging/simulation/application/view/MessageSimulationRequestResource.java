package com.messaging.simulation.application.view;

import com.messaging.simulation.domain.MessageSimulation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessageSimulationRequestResource {

    private String username;

    private String text;

    private Integer timeout;

    public MessageSimulation toDomain() {
        return MessageSimulation.messageSimulation(
                this.username,
                this.text,
                this.timeout);
    }

}
