package com.bsuir.its.service.impl;

import com.bsuir.its.convertors.RoleDTOConverter;

import com.bsuir.its.convertors.UserDTOConverter;
import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.model.User;
import com.bsuir.its.dao.repository.RoleRepository;
import com.bsuir.its.dao.repository.UserRepository;
import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.UserDTO;
import com.bsuir.its.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserDTOConverter userDTOConverter;

    @Value("${app.default.admin.name}")
    private String defaultAdminName;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        userDTOConverter = new UserDTOConverter();
    }

    @Override
    public List<UserDTO> getUsers() {

        List<User> users= userRepository.findAll();

        List<UserDTO> userDTOS = users.stream()
                .map(userDTOConverter::convert)
                .collect(Collectors.toList());

        //userDTOS.removeIf(userDTO->userDTO.getName().equals(defaultAdminName));

        return userDTOS;
    }

    @Override
    public UserDTO updateUserRoles(String userId, List<RoleDTO> rolesDTO) {

        List<Role> roles = roleRepository.findAll(rolesDTO.stream().map(RoleDTO::getId).collect(Collectors.toList()));

        User user =  userRepository.findUserById(userId);
        user.setRoles(roles);
        userRepository.save(user);

        return userDTOConverter.convert(user);
    }

    @Override
    public List<RoleDTO> getRoles() {
        RoleDTOConverter roleDTOConverter = new RoleDTOConverter();

        return roleRepository.findAll().stream().map(roleDTOConverter::convert).collect(Collectors.toList());
    }
}
