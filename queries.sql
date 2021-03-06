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

SELECT DISTINCT name, description
FROM techjobs.skill
INNER JOIN job_skills ON skill.id = job_skills.skills_id
WHERE job_skills.skills_id IS NOT NULL
ORDER BY name;