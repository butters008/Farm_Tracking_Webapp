package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CalfFormBean {
    private Integer id;
    private String calfId1;
    private String calfId2;
    private String breed;
    private String calfSex;
    private Integer birthWeight;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeath;
    private Integer weanWeight;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weanDate;
    private String calfStatus;

    //For the parents
    private String mother;
    private String father;






}
