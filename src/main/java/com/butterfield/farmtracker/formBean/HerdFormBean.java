package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class HerdFormBean {
    private Integer id;

    private String animalId1;
    private String animalId2;
    private String animalType;
    private String herdStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeath;
    private String boughtFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate boughtDate;
}
