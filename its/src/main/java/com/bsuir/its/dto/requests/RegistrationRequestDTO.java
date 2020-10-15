package com.bsuir.its.dto.requests;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.model.Section;
import com.bsuir.its.dao.model.enums.Degree;
import com.bsuir.its.dto.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.repository.query.StringQueryParameterBinder;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "[a-zA-Z]+[0-9]+[?!_]+")
    private String password;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+[0-9]+[?!_]+")
    private String confirmPassword;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String surname;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String patronymic;

    @Pattern(regexp = "[+]?[0-9]+")
    @Size(min = 3,max = 12)
    private String phone;

    @Size(max=2,min = 2)
    @Pattern(regexp = "[a-zA-Z]+]")
    private String countryCode;

    @NotNull
    @NotBlank
    private String jobAddress;

    @NotNull
    @NotBlank
    private String degreeId;

    @NotNull
    @NotBlank
    private List<String> sectionsIds;

    @NotNull
    @NotBlank
    private String organization;

}
