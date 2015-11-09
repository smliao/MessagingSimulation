package com.messaging.simulation.domain.repository;

import com.messaging.simulation.domain.MessageSimulation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageSimulationRepository extends MongoRepository<MessageSimulation, String> {

    List<MessageSimulation> deleteByUsername(String username);
}
