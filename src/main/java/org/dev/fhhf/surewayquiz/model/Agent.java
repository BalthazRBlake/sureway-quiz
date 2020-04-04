package org.dev.fhhf.surewayquiz.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name="agent")
public class Agent implements Serializable {

    private static final long serialVersionUID = 2345L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agent_id", nullable = false)
    private Integer agentId;
    private String name;
    private String password;
    private Integer score;

    public Agent() {
    }

    public Agent(Integer agentId, String name, Integer score) {
        this.agentId = agentId;
        this.name = name;
        this.score = score;
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

    @Override
    public String toString() {
        return "Agent{" +
                "agentId=" + agentId +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
