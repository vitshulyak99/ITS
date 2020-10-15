package com.bsuir.its.convertors;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dto.RoleDTO;

public class RoleDTOConverter {

    public RoleDTO convert(Role role){
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
