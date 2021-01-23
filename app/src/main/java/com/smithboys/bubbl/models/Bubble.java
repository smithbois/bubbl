package com.smithboys.bubbl.models;

import com.smithboys.bubbl.database.GlobalBubbles;

import java.util.HashSet;
import java.util.Set;

public class Bubble {
    private Integer id;
    private String name; // Display name of the bubble
    private Integer creator; // ID of user that created the bubble (only one that can delete)
    private Set<Integer> users = new HashSet<Integer>(); // List of users (by id) in the bubble
    private Integer riskLevel; // risk level of the group, based on exposures, 1 to 5

    public Bubble(String name, Integer creator) {
        this.id = GlobalBubbles.getBubbleCount();
        this.name = name;
        this.creator = creator;
        this.users.add(this.creator);
        this.riskLevel = 1;

        GlobalBubbles.addBubble(this);
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Set<Integer> getUsers() {
        return users;
    }

    public void addUser(Integer id) {
        this.users.add(id);
    }

    public void removeUser(Integer id) {
        this.users.remove(id);
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }
}
