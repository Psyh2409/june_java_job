package com.gmail.psyh2409.june_java_job.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class URLOfCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private URL url;
    @ManyToOne (cascade = {CascadeType.ALL})
    private Company company;


}
