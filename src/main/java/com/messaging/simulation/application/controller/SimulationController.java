package com.messaging.simulation.application.controller;

import com.messaging.simulation.application.controller.path.SimulationPaths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SimulationPaths.ROOT_PATH)
public class SimulationController {

    @RequestMapping(value = SimulationPaths.TEST_PATH, method = RequestMethod.GET)
    public ResponseEntity<String> testingSpringBoot() {
        return new ResponseEntity<>("Hello World Spring Boot", HttpStatus.OK);
    }
}
