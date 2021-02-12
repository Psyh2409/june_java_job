package com.gmail.psyh2409.june_java_job.repositories;

import com.gmail.psyh2409.june_java_job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
