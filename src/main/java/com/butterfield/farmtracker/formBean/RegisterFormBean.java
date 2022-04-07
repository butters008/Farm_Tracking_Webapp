package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
public class RegisterFormBean {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirm;

}
