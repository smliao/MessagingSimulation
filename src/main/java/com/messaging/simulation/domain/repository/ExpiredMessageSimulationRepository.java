package com.messaging.simulation.domain.repository;

import com.messaging.simulation.domain.ExpiredMessageSimulation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpiredMessageSimulationRepository extends MongoRepository<ExpiredMessageSimulation, String> {

}
