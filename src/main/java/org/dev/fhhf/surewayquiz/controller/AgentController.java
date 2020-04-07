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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/sw/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping()
    public String getAllAgents(Model model){
        List<Agent> agents = agentService.findAllAgents();
        Collections.sort(agents);
        model.addAttribute("agents", agents);
        return "agents";
    }

    @GetMapping("/{id}/initForm")
    public String initUpdateForm(@PathVariable("id") int id, Model model){
        Agent agent;

        agent = id != 0 ? agentService.findAgentById(id)
                        : new Agent(0,"","test001",
                         0,"ROLE_USER",true,0, "A:");

        model.addAttribute("agent", agent);
        return "editAgent";
    }

    @PostMapping("/{id}/update")
    public String updateAgentInfo(@PathVariable("id") int id, Agent agent){
        agentService.saveAgent(agent);
        return "redirect:/sw/agent";
    }

    @GetMapping("/{id}/quizDetails")
    public String quizDetails(@PathVariable("id")int id, Model model){
        Agent agent = agentService.findAgentById(id);
        List<String> wrongOnes = new ArrayList<>();
        //System.out.println(agent.getAnswers());
        //System.out.println(agent.getAnswers().length());
        if(agent.getAnswers().length() > 2) {
            String ans = agent.getAnswers().substring(2);
            //System.out.println("SUB ST   :::   " + ans);
            String[] answers = ans.split(",");

            for (int i = 0; i < answers.length; i++) {
                if (answers[i].charAt(0) == '0') {
                    wrongOnes.add( (i+1) + " selected: " + answers[i].substring(2) );
                }
            }
        }
        model.addAttribute("wrongOnes", wrongOnes);
        return "agentQuizDetails";
    }
}
