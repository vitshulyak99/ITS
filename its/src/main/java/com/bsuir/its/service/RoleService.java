package com.bsuir.its.service;

import com.bsuir.its.dao.model.Role;

import java.util.List;

public interface RoleService {
    Role create(String name);
    Role getById(String id);
    List<Role> getAll();
    void delete(String id);
    Role update(Role update);
}
