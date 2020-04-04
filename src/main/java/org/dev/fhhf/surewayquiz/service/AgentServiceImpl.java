package org.dev.fhhf.surewayquiz.service;

import org.dev.fhhf.surewayquiz.model.Agent;
import org.dev.fhhf.surewayquiz.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepo;

    @Override
    public List<Agent> findAllAgents() {
        return agentRepo.findAll();
    }

    @Override
    public Agent findAgentById(int id) {
        return agentRepo.findById(id).get();
    }

    @Override
    public Agent findAgentByName(String agentName) {
        return agentRepo.findByName(agentName).get();
    }

    @Override
    public Agent saveAgent(Agent agent) {
        return agentRepo.save(agent);
    }
}
