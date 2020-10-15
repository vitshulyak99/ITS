package com.bsuir.its.dto.requests;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class NameRequestDTO {

    @Email
    String email;

    @Length(max = 20, min =  3)
    @NotBlank
    String name;

    @NotBlank
    @Length(max = 20, min =  3)
    String surname;

    @NotBlank
    @Length(max = 20, min =  3)
    String patronymic;
}
