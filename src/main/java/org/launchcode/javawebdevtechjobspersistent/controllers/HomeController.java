package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerComparator;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

//    public String sortEmployers() {
//        Comparator comparator = new EmployerComparator();
//        EmployerRepository sortedRepository = employerRepository.sort(comparator);
//
//    }

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        //findAll(Sort.by(Sort.Direction.ASC, "seatNumber"))
        model.addAttribute("employers", employerRepository.findAll(Sort.by("name")));
        //model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll(Sort.by("name")));
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, params = {"employerId"})
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, int employerId) {

        if (errors.hasFieldErrors("name")) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            model.addAttribute(newJob);
            return "add";
        }

        Optional<Employer> result = employerRepository.findById(employerId);

        if (result.isEmpty()) {
            model.addAttribute("title", "No Employer Found, Try Again");
            model.addAttribute(new Job());
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
//          else if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//              return "/add";
//        }
//        else if (errors.hasErrors()) {
//            model.addAttribute(errors);
//            //model.addAttribute(new Job());
//            model.addAttribute("employers", employerRepository.findAll());
//            model.addAttribute("skills", skillRepository.findAll());
//            return "add";
//        }
        else {
            Employer employer = result.get();
            newJob.setEmployer(employer);
            jobRepository.save(newJob);
            return "redirect:";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, params = {"employerId", "skills"})
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors,
                                    Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasFieldErrors("name")) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            model.addAttribute(newJob);
            return "add";
        }

        Optional<Employer> result = employerRepository.findById(employerId);

        if (result.isEmpty()) {
            model.addAttribute("title", "No Employer Found, Try Again");
            model.addAttribute(new Job());
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        }
//        else if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Job");
//            return "/add";
//        }
//        else if (errors.hasErrors()) {
//            model.addAttribute(errors);
//            model.addAttribute("employers", employerRepository.findAll());
//            model.addAttribute("skills", skillRepository.findAll());
//            return "add";
//        }
        else {
            Employer employer = result.get();
            newJob.setEmployer(employer);

            List<Skill> skillsList = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillsList);
            jobRepository.save(newJob);
            return "redirect:";
        }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> result = jobRepository.findById(jobId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Job ID: " + jobId);
        } else {
            Job job = result.get();
            model.addAttribute("title", "Job Listing: " + job.getName());
            model.addAttribute("job", job);
        }
        return "view";
    }
}

