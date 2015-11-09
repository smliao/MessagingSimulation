package com.messaging.simulation.application.view;

import com.messaging.simulation.application.view.validator.ValidationMessage;
import com.messaging.simulation.domain.MessageSimulation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MessageSimulationRequestResource {

    @NotEmpty(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    private String username;

    @NotEmpty(message = ValidationMessage.MAY_NOT_BE_EMPTY)
    private String text;

    @Digits(integer = 10, fraction = 0, message = ValidationMessage.INVALID_INTEGER)
    private Integer timeout;

    public MessageSimulation toDomain() {
        return MessageSimulation.messageSimulation(
                this.username,
                this.text,
                this.timeout);
    }
}
