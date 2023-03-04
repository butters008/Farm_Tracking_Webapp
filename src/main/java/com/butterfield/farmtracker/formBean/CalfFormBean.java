package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CalfFormBean {
    private Integer id;

    @NotBlank(message="Calf Id 1 - Required")
    private String calfId1;

    private String calfId2;

    @NotBlank(message="Calf Breed - Required")
    private String breed;

    @NotBlank(message="Calf Sex - Required")
    private String calfSex;

//    @NotNull(message = "Need to have a Birth Weight")
    private Integer birthWeight;

    @NotNull(message = "Need to have a Date-of-Birth")
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
