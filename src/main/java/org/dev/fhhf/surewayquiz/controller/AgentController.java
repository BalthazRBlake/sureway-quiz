package org.dev.fhhf.surewayquiz.controller;

import org.dev.fhhf.surewayquiz.model.Agent;
import org.dev.fhhf.surewayquiz.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sw/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping()
    public String getAllAgents(Model model){
        List<Agent> agents = agentService.findAllAgents();
        model.addAttribute("agents", agents);
        return "agents";
    }

    @GetMapping("/{id}/initForm")
    public String initUpdateForm(@PathVariable("id") int id, Model model){
        Agent agent = agentService.findAgentById(id);
        model.addAttribute("agent", agent);
        return "editagent";
    }

    @PostMapping("/{id}/update")
    public String updateAgentInfo(@PathVariable("id") int id, Agent agent){
        agent.setAgentId(id);
        agentService.saveAgent(agent);
        return "redirect:/sw/agent";
    }
}
