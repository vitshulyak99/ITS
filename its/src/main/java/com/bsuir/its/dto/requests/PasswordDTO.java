package com.bsuir.its.dto.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PasswordDTO {

    @Email
    @NotBlank
    String email;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+[0-9]+[?!_]+")
    @Size(max = 20, min = 6)
    String oldPassword;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+[0-9]+[?!_]+")
    @Size(max = 20, min = 6)
    String newPassword;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+[0-9]+[?!_]+")
    @Size(min = 6,max = 20)
    String confirmPassword;

}
