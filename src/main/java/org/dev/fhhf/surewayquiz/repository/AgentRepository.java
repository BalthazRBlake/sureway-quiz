package org.dev.fhhf.surewayquiz.repository;

import org.dev.fhhf.surewayquiz.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Integer> {

    Optional<Agent> findByName(String agentName);
}
