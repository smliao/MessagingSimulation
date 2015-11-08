package com.messaging.simulation.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Document(collection = MessageSimulation.COLLECTION_NAME)
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MessageSimulation {

    public static final String COLLECTION_NAME = "messageSimulation";

    @Id
    private String id;

    private String username;

    private String text;

    private String expiration_date;

    public static MessageSimulation messageSimulation(String username, String text, Integer timeout) {
        return new MessageSimulation(UUID.randomUUID().toString(),
                username,
                text,
                getExpirationAge(timeout));
    }

    private static String getExpirationAge(Integer timeout) {
        return LocalDateTime.now()
                .plus(timeout, ChronoUnit.SECONDS)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
