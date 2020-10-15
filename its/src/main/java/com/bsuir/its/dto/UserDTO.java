package com.bsuir.its.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UserDTO {

    private String id;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private List<RoleDTO> roles;
}
