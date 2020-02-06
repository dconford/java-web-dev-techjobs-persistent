package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @GetMapping("")
    public String displayAllEmployers (Model model) {
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Employer");
            //model.addAttribute("errorMsg", "Bad data!");
            model.addAttribute(errors);
            //model.addAttribute(new Employer());
            return "employers/add";
        }

        employerRepository.save(newEmployer);
        return "redirect:../add";
    }

    // displayViewEmployer will be in charge of rendering a page to view the contents
    // of an individual employer object. It will make use of that employer object’s id
    // field to grab the correct information from employerRepository. optEmployer
    // currently initialized to null. Replace this using the .findById() method with
    // the right argument to look for the given employer object from the data layer.

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional <Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}

