package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@ToString
public class CalfFormBean {
    private Integer id;
    private String calfId1;
    private String calfId2;
    private Integer birthWeight;

}
