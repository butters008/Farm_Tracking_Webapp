package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CalfFormBean {
    private Integer id;

    @NotBlank(message="Calf Id 1 - Required")
    private String calfId1;

    @NotBlank(message="Calf Id 2 - Required")
    private String calfId2;

    @NotBlank(message="Calf Breed - Required")
    private String breed;

    @NotBlank(message="Calf Sex - Required")
    private String calfSex;

//    @NotBlank(message="Birth Weight - Required")
    private Integer birthWeight;

//    @NotBlank(message="Birthdate - Required")
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
