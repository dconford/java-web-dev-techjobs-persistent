## Part 1: Test it with SQL

SHOW COLUMNS FROM techjobs.job;

--   Results:
--   NAME     TYPE          NULLABLE      KEYS
--   id       int           Not Nullable  PRIMARY KEY
--   employer varchar(255)
--   name     varchar(255)
--   skills   varchar(255)

## Part 2: Test it with SQL

SELECT * FROM techjobs.employer
WHERE location = "St. Louis";

--   Results
--   id     name      location
--   27     Boeing    St. Louis

## Part 3: Test it with SQL
DROP TABLE `techjobs`.`jobs`;
## Part 4: Test it with SQL

@PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, int employerId,
                                    @RequestParam(required = false) List<Integer> skills) {

                                    if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";


        }
//        Optional<Employer> tempEmp = employerRepository.findById(employerId);
//        if (tempEmp.isPresent()){
//            Employer employer = tempEmp.get();
//            newJob.setEmployer(employer);
//        }
// OR ALL IN ONE LINE.....
newJob.setEmployer(employerRepository.findById(employerId).get());

List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills((List<Skill>) skillObjs);        jobRepository.save(newJob);
        return "redirect:";
    }