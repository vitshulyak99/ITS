package com.bsuir.its.convertors;


import com.bsuir.its.dao.model.User;
import com.bsuir.its.dto.UserDTO;

import java.util.stream.Collectors;

public class UserDTOConverter {
    public UserDTO convert(User user){
        RoleDTOConverter roleDTOConverter = new RoleDTOConverter();

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .patronymic(user.getPatronymic())
                .surname(user.getSurname())
                .roles(user.getRoles().stream().map(roleDTOConverter::convert).collect(Collectors.toList()))
                .build();
    }
}
