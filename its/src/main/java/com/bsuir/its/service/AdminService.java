package com.bsuir.its.service;

import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.UserDTO;


import java.util.List;

public interface AdminService {

    public List<UserDTO> getUsers();
    public UserDTO updateUserRoles(String id, List<RoleDTO> roleDTO);
    public List<RoleDTO> getRoles();
}
