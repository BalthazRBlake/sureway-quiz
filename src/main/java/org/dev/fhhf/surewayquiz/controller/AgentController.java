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

    @PostMapping("/update/{id}")
    public String unpdateAgentInfo(@PathVariable("id") int id, Agent agent){

        return "redirect/agents";
    }
}
