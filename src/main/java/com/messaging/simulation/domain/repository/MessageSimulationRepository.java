package com.messaging.simulation.domain.repository;

import com.messaging.simulation.domain.MessageSimulation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageSimulationRepository extends MongoRepository<MessageSimulation, String> {

    List<MessageSimulation> findByUsername(String username);
}
