package org.dev.fhhf.surewayquiz.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name="agent")
public class Agent implements Serializable, Comparable<Agent> {

    private static final long serialVersionUID = 2345L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agent_id", nullable = false)
    private Integer agentId;
    private String name;
    private String password;
    private Integer score;
    private String roles;
    private boolean active;
    private Integer attempts;

    public Agent() {
    }

    public Agent(Integer agentId, String name, String password, Integer score, String roles, boolean active, Integer attempts) {
        this.agentId = agentId;
        this.name = name;
        this.password = password;
        this.score = score;
        this.roles = roles;
        this.active = active;
        this.attempts = attempts;
    }

    public void increaseScore(int score){
        this.score = score + 1;
    }

    @Override
    public int compareTo(Agent agent) {
        return this.getName().compareTo(agent.getName());
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId=" + agentId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", roles='" + roles + '\'' +
                ", active=" + active +
                ", attempts=" + attempts +
                '}';
    }
}
