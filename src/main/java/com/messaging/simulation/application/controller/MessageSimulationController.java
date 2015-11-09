package com.messaging.simulation.application.controller;

import com.messaging.simulation.application.controller.path.MessageSimulationPaths;
import com.messaging.simulation.application.service.MessageSimulationService;
import com.messaging.simulation.application.view.MessageSimulationByUsernameResponseResource;
import com.messaging.simulation.application.view.MessageSimulationIdResponseResource;
import com.messaging.simulation.application.view.MessageSimulationRequestResource;
import com.messaging.simulation.application.view.MessageSimulationResponseResource;
import com.messaging.simulation.domain.MessageSimulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = MessageSimulationPaths.ROOT_PATH, method = RequestMethod.GET)
public class MessageSimulationController {

    @Autowired
    private MessageSimulationService messageSimulationService;

    @RequestMapping(value = MessageSimulationPaths.TEST_PATH, method = RequestMethod.GET)
    public ResponseEntity<String> testingSpringBoot() {

        log.info("Hello World Spring Boot");

        return new ResponseEntity<>("Hello World Spring Boot", HttpStatus.OK);
    }

    @RequestMapping(value = MessageSimulationPaths.CHAT, method = RequestMethod.POST)
    public ResponseEntity<MessageSimulationIdResponseResource> create(@Valid @RequestBody MessageSimulationRequestResource messageSimulationRequestResource) {

        log.info("Create new chat {}", messageSimulationRequestResource);

        MessageSimulation messageSimulation = messageSimulationRequestResource.toDomain();

        MessageSimulation createdMessageSimulation = messageSimulationService.create(messageSimulation);

        log.info("Result {} saved in messageSimulationRepository", createdMessageSimulation);

        MessageSimulationIdResponseResource result = MessageSimulationIdResponseResource.toResource(createdMessageSimulation);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = MessageSimulationPaths.ID, method = RequestMethod.GET)
    public ResponseEntity<MessageSimulationResponseResource> findOne(@PathVariable String id) {

        log.info("Searching for chat on id: {}", id);

        MessageSimulation messageSimulation = messageSimulationService.findById(id);

        log.info("Searched chat for id {} returned {}", id, messageSimulation);

        MessageSimulationResponseResource result = MessageSimulationResponseResource.toResource(messageSimulation);

        log.info("MessageSimulationResponseResource result: {}", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = MessageSimulationPaths.USER_NAME, method = RequestMethod.GET)
    public ResponseEntity<Collection<MessageSimulationByUsernameResponseResource>> findByUsername(@PathVariable String username) {

        log.info("Find all chat with username : {}", username);

        List<MessageSimulation> messageSimulationList = messageSimulationService.findByUsername(username);

        log.info("Result of all {} chats: {}", username, messageSimulationList);

        List<MessageSimulationByUsernameResponseResource> results =
                MessageSimulationByUsernameResponseResource.toResource(messageSimulationList);

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
