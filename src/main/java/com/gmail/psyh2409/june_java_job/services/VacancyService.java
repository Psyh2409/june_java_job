package com.gmail.psyh2409.june_java_job.services;

import com.gmail.psyh2409.june_java_job.Parser;
import com.gmail.psyh2409.june_java_job.model.Vacancy;
import com.gmail.psyh2409.june_java_job.repositories.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    @Transactional
    public void addVacancy(String surl){
        vacancyRepository.saveAll(Parser.getVacancies(surl));
    }

    @Transactional(readOnly = true)
    public List<Vacancy> findAll(){return vacancyRepository.findAll();}
}
