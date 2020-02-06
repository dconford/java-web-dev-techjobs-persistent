package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.launchcode.javawebdevtechjobspersistent.models.JobData;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, Iterable> tableChoices = new HashMap<>();

    public ListController () {

        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("skill", "Skill");

        //tableChoices.put("all", "View All");
        //tableChoices.put("employer", employerRepository.findAll());
        //tableChoices.put("skill", skillRepository.findAll());

    }

//    @RequestMapping(value = "")
//    public String list(Model model) {
//        model.addAttribute("columns", columnChoices);
//        model.addAttribute("tableChoices", tableChoices);
//        model.addAttribute("employers", JobData.getAllEmployers());
//        model.addAttribute("locations", JobData.getAllLocations());
//        model.addAttribute("positions", JobData.getAllPositionTypes());
//        model.addAttribute("skills", JobData.getAllCoreCompetency());
//
//        return "list";
//    }

    @RequestMapping("")
    public String list(Model model) {

        model.addAttribute("columns", columnChoices);
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());

        //model.addAttribute("tableChoices", tableChoices);

        return "list";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Job> jobs;
        if (column.toLowerCase().equals("all")){
            jobs = jobRepository.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}

/*
<tr>
        <td>
            <li>
                <a th:href="@{/list/jobs(column='All',value='View All')}">View All</a>
            </li>
        </td>
        <td>
            <li th:each="employer : ${employers}">
                <a th:href="@{/list/jobs(column=employer,value=${employer.name})}" th:text="${employer.name}"></a>
            </li>
        </td>
        <td>
            <li th:each="skill : ${skills}">
                <a th:href="@{/list/jobs(column=skill,value=${skill.name})}" th:text="${skill.name}"></a>
            </li>
        </td>
    </tr>
 */