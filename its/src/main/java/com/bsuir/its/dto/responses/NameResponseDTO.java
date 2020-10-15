package com.bsuir.its.dto.responses;

import lombok.Data;

import org.hibernate.validator.constraints.Length;

@Data
public class NameResponseDTO {
    @Length(max = 20, min =  3)

    String name;


    @Length(max = 20, min =  3)
    String surname;


    @Length(max = 20, min =  3)
    String patronymic;
}
