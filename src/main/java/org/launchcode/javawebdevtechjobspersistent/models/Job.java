package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Job extends AbstractEntity{


    @ManyToOne
    @NotNull(message = "Employer is Required!")
    private Employer employer;


    private Skill skills;

    public Job() {
    }

    public Job(Employer anEmployer, Skill someSkills) {
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Skill getSkills() {
        return skills;
    }

    public void setSkills(Skill skills) {
        this.skills = skills;
    }
}
