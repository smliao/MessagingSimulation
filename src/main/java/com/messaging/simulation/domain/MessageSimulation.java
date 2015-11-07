package com.messaging.simulation.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = MessageSimulation.COLLECTION_NAME)
@ToString
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MessageSimulation {

    public static final String COLLECTION_NAME = "messageSimulation";

    @Id
    private String id;

    private String username;

    private String text;

    private Integer timeout;

    public static MessageSimulation messageSimulation(String username, String text, Integer timeout){
        return new MessageSimulation(UUID.randomUUID().toString(),
                username,
                text,
                timeout);
    }
}
