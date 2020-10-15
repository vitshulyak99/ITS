package com.bsuir.its.dto.responses;

import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String Id;
    private String email;
    private String token;
    private List<RoleDTO> roles;
    private List<SectionDTO> sections;
    private String fio;
}
