package com.bsuir.its.dto.admin;

import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCreateUserDTO {

    String email  ;
    RoleDTO[] roles;
    SectionDTO[] sections;
}
