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
public class HerdFormBean {
    private Integer id;

    @NotBlank(message="Animal Id 1 - Required")
    private String animalId1;

    private String animalId2;

    @NotBlank(message = "Please pick either Cow or Bull")
    private String animalType;

    @NotBlank(message = "Please select the breed")
    private String breed;

    @NotBlank(message = "Please select a status for the herd")
    private String herdStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeath;

    private String boughtFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate boughtDate;

    private LocalDate animalImage;

}
