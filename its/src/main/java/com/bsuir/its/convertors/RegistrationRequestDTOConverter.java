package com.bsuir.its.convertors;

import com.bsuir.its.dto.requests.RegistrationRequestDTO;
import com.bsuir.its.dao.model.User;

import java.util.Locale;

public class RegistrationRequestDTOConverter {

    public RegistrationRequestDTOConverter() {
    }

    public static User convert(RegistrationRequestDTO userDTO){
        User user = new User();

        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setActive(true);
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setPhone(userDTO.getPhone());
        user.setJobAddress(userDTO.getJobAddress());
        user.setCountry(new Locale("Russian",userDTO.getCountryCode()).getCountry());

        return user;
    }



}
