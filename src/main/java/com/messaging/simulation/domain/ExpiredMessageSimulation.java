package com.messaging.simulation.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = ExpiredMessageSimulation.COLLECTION_NAME)
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ExpiredMessageSimulation {

    public static final String COLLECTION_NAME = "expiredMessageSimulation";

    @Id
    private String id;

    private String username;

    private String text;

    private String expiration_date;

    public static ExpiredMessageSimulation from(MessageSimulation messageSimulation) {
        return new ExpiredMessageSimulation(
                messageSimulation.getId(),
                messageSimulation.getUsername(),
                messageSimulation.getText(),
                messageSimulation.getExpiration_date());
    }
}

