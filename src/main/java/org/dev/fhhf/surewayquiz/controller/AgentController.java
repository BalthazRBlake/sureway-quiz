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

import javax.jws.WebParam;
import java.util.*;

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
        //if(agent.getAnswers().length() > 2) {
        String ans = agent.getAnswers().substring(2);
        //System.out.println("SUB ST   :::   " + ans);
        String[] answers = ans.split(",");
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].charAt(0) == '0' && answers[i].charAt(1) == ' ') {
                wrongOnes.add( answers[i].substring(2) );
            }
        }
        //}
        model.addAttribute("wrongOnes", wrongOnes);
        return "agentQuizDetails";
    }

    @GetMapping("/wrongGraph")
    public String wrongGraph(Model model){
        List<Agent> agents = agentService.findAllAgents();
        Map<Integer, Integer> wrongSta = new HashMap<>();
        for(Agent agent : agents){

            String ans = agent.getAnswers().substring(2);
            String[] answers = ans.split(",");
            for (int i = 0; i < answers.length; i++) {
                if (answers[i].charAt(0) == '0' && answers[i].charAt(1) == ' ') {
                    char c1 = answers[i].charAt(2);
                    char c2 = answers[i].charAt(3);
                    char c3 = answers[i].charAt(4);
                    int q1 = -1, q2 = -1, q3 = -1;
                    int questionNumber = 0;

                    q1 = Integer.parseInt(String.valueOf(c1));

                    if(answers[i].charAt(4) >= 48 && answers[i].charAt(4) <= 57){
                        System.out.println("100   :::   ");
                        q3 = Integer.parseInt(String.valueOf(c3));
                        q2 = Integer.parseInt(String.valueOf(c2));
                        questionNumber = q1*100 + q2*10 + q3;
                    }else
                    if(answers[i].charAt(3) >= 48 && answers[i].charAt(3) <= 57){
                        q2 = Integer.parseInt(String.valueOf(c2));
                        questionNumber = q1*10 + q2;
                    }else{
                        questionNumber = q1;
                    }

                    if(wrongSta.containsKey(questionNumber)){
                        int val = wrongSta.get(questionNumber);
                        wrongSta.replace(questionNumber, ++val);
                    }else{
                        wrongSta.put(questionNumber, 1);
                    }
                }
            }
        }
        //System.out.println(wrongSta);
        model.addAttribute("wrongSta", wrongSta);
        return "wrongGraph";
    }
}
