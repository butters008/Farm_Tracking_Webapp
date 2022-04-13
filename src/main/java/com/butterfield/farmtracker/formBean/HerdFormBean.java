package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.servlet.tags.form.CheckboxTag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class HerdFormBean {
    private Integer id;

    private String animalId1;
    private String animalId2;
    private String animalType;
    private String herdStatus;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String boughtFrom;
    private LocalDate boughtDate;

//    private CheckboxTag checkbox;

}
