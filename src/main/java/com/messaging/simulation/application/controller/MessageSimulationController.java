package com.messaging.simulation.application.controller;

import com.messaging.simulation.application.controller.path.MessageSimulationPaths;
import com.messaging.simulation.application.service.MessageSimulationService;
import com.messaging.simulation.application.view.MessageSimulationRequestResource;
import com.messaging.simulation.application.view.MessageSimulationResponseResource;
import com.messaging.simulation.domain.MessageSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = MessageSimulationPaths.ROOT_PATH, method = RequestMethod.GET)
public class MessageSimulationController {

    @Autowired
    private MessageSimulationService messageSimulationService;

    @RequestMapping(value = MessageSimulationPaths.TEST_PATH, method = RequestMethod.GET)
    public ResponseEntity<String> testingSpringBoot() {
        return new ResponseEntity<>("Hello World Spring Boot", HttpStatus.OK);
    }

    @RequestMapping(value = MessageSimulationPaths.CHAT, method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(@RequestBody MessageSimulationRequestResource messageSimulationRequestResource){

        MessageSimulation messageSimulation = messageSimulationRequestResource.toDomain();

        messageSimulation = messageSimulationService.create(messageSimulation);

        Map<String, Object> result = MessageSimulationResponseResource.toCreateResource(messageSimulation);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @RequestMapping(value = MessageSimulationPaths.ID, method = RequestMethod.GET)
    public ResponseEntity<MessageSimulationResponseResource> findOne(@PathVariable String id){

        MessageSimulation messageSimulation = messageSimulationService.findById(id);

        MessageSimulationResponseResource result = MessageSimulationResponseResource.toResource(messageSimulation);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
