package com.butterfield.farmtracker.formBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
public class RegisterFormBean {
    private Integer id;

    @NotBlank(message = "Email is required")
    @Email(message = "Not a Valid Email")
    private String email;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Length(min = 8, max = 20, message = "Password must be between 8 to 20 character")
    @NotBlank(message = "Password is required")
    private String password;

    @Length(min = 8, max = 20, message = "Password must be between 8 to 20 character")
    @NotBlank(message = "Password Confirm is required")
    private String passwordConfirm;

}
