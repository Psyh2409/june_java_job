package com.gmail.psyh2409.june_java_job.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne (cascade = CascadeType.ALL)
    private Company company;

    @ManyToOne (cascade = CascadeType.ALL)
    private ContactPerson contactPerson;
    @Column
    private String publication;
    @Column
    private String vacancyName;
    @Column
    private String textOfVacancy;
}
