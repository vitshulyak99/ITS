package com.bsuir.its.dto.requests;

import com.bsuir.its.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleDTO {

    private String id;
    private List<RoleDTO> roles;
}
