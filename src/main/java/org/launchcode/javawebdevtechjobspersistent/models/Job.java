package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Job extends AbstractEntity{


    @ManyToOne
    @NotNull(message = "Employer is Required!")
    private Employer employer;

    @ManyToOne
    private Skill skill;

    public Job() {
    }

    public Job(Employer anEmployer, Skill someSkills) {
        this.employer = anEmployer;
        this.skill = someSkills;
    }

    // Getters and setters.

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
