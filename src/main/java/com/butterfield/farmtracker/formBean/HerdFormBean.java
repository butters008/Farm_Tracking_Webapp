package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.servlet.tags.form.CheckboxTag;

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
    private Date dateOfBirth;
    private Date dateOfDeath;

//    private CheckboxTag checkbox;

}
