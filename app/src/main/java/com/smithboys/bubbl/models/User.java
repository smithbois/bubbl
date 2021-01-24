package com.smithboys.bubbl.models;

import android.graphics.Bitmap;

import com.smithboys.bubbl.database.GlobalBubbles;
import com.smithboys.bubbl.database.GlobalUsers;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    private int profilePic;

    private Boolean isTestVerified; // True if test was verified, false if self-reported
    private Boolean isInfected; // True if user currently has covid-19
    private Boolean isVaccinated; // True if user has received the vaccine

    private LocalDate dateLastTested; // Date of most recent test
    private LocalDate  dateInfected; // Date the user started experiencing covid symptoms
    private LocalDate  dateVaccinated; // Date the user received the vaccine or recovered

    private Integer riskLevel; // risk level of the group, based on exposures, 1 to 5

    private Set<Integer> bubbles = new HashSet<Integer>(); // List of bubble ids the user is in

    public User(String email, String firstName, String lastName, String password) {
        this.id = GlobalUsers.getUserCount();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

        this.isTestVerified = false;
        this.isInfected = false;
        this.isVaccinated = false;

        this.dateLastTested = null;
        this.dateInfected = null;
        this.dateVaccinated = null;

        this.riskLevel = 2;

        GlobalUsers.addUser(this);
    }

    public Integer getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public Boolean getTestVerified() {
        return isTestVerified;
    }

    public void setTestVerified(Boolean testVerified) {
        isTestVerified = testVerified;
    }

    public Boolean getInfected() {
        return isInfected;
    }

    public void setInfected(Boolean infected) {
        isInfected = infected;
    }

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public LocalDate getDateLastTested() {
        return dateLastTested;
    }

    public void setDateLastTested(LocalDate dateLastTested) {
        this.dateLastTested = dateLastTested;
    }

    public LocalDate getDateInfected() {
        return dateInfected;
    }

    public void setDateInfected(LocalDate dateInfected) {
        this.dateInfected = dateInfected;
    }

    public LocalDate getDateVaccinated() {
        return dateVaccinated;
    }

    public void setDateVaccinated(LocalDate dateVaccinated) {
        this.dateVaccinated = dateVaccinated;
    }

    public Set<Integer> getBubbles() {
        return this.bubbles;
    }

    public void joinBubble(Integer id) {
        this.bubbles.add(id);
        Bubble bubble = GlobalBubbles.queryByID(id);
        bubble.addUser(this.id);
    }

    public void leaveBubble(Integer id) {
        this.bubbles.remove(id);
        Bubble bubble = GlobalBubbles.queryByID(id);
        bubble.removeUser(this.id);
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }
}
