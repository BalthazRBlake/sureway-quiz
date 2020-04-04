package org.dev.fhhf.surewayquiz.service;

import org.dev.fhhf.surewayquiz.model.Agent;

import java.util.List;

public interface AgentService {

    List<Agent> findAllAgents();

    Agent findAgentByName(String agentName);

    Agent findAgentById(int id);

    Agent saveAgent(Agent agent);
}
