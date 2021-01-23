package com.smithboys.bubbl.models;

import com.smithboys.bubbl.database.GlobalBubbles;

import java.util.HashSet;
import java.util.Set;

public class Bubble {
    private Integer id;
    private String name; // Display name of the bubble
    private Integer creator; // ID of user that created the bubble (only one that can delete)
    private Set<Integer> users = new HashSet<Integer>(); // List of users (by id) in the bubble

    public Bubble(String name, Integer creator) {
        this.id = GlobalBubbles.getBubbleCount();
        this.name = name;
        this.creator = creator;
        this.users.add(this.creator);

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
}
