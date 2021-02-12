package com.gmail.psyh2409.june_java_job.repositories;

import com.gmail.psyh2409.june_java_job.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

}
