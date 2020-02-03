package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

//    @OneToMany(mappedBy = "skill")
//    private final List<Job> jobs = new ArrayList<>();

    @NotBlank(message = "Enter a Skill Description")
    @Size(min = 3, max = 80, message = "Skill Description must be between 3 and 80 characters")
    private String description;

    public Skill() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public List<Job> getJobs() {
//        return jobs;
//    }
}

