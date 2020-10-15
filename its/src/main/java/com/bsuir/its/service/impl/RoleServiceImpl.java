package com.bsuir.its.service.impl;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.repository.RoleRepository;
import com.bsuir.its.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role create(String name) {
        return roleRepository.save(new Role(name));
    }

    @Override
    public Role getById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(String id) {
        roleRepository.delete(id);
    }

    @Override
    public Role update(Role update) {
        return roleRepository.save(update);
    }
}
