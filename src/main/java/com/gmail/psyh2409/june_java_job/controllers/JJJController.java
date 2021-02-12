package com.gmail.psyh2409.june_java_job.controllers;

import com.gmail.psyh2409.june_java_job.services.VacancyService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@Data
public class JJJController {
    private final VacancyService vacancyService;

    public JJJController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @RequestMapping("/")
    public String getIndex(){
        return "Hello world!";
    }

    @PostConstruct
    public void addAllVacancies(){
        vacancyService.addVacancy("");
    }

    public void printAllVacancies(){
        vacancyService.findAll().forEach(System.out::println);
    }
}
