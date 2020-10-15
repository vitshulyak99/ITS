package com.bsuir.its.dto.responses;

import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersDTO {
    private List<UserDTO> users;
    private List<RoleDTO> allRoles;
}
