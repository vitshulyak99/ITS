package com.bsuir.its.service.impl;

import com.bsuir.its.dto.UserDTO;
import com.bsuir.its.exception.PasswordConfirmationException;
import com.bsuir.its.dao.model.User;
import com.bsuir.its.dao.repository.UserRepository;
import com.bsuir.its.dto.requests.NameRequestDTO;
import com.bsuir.its.dto.responses.NameResponseDTO;
import com.bsuir.its.dto.requests.PasswordDTO;
import com.bsuir.its.service.AccountService;
import com.bsuir.its.service.exception.InvalidOldPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public NameResponseDTO getName(String email) {

        User user = userRepository.findUserByEmail(email);

        if (user !=null){
            NameResponseDTO nameResponseDTO = new NameResponseDTO();
            String  name = user.getName(),
                    surname = user.getSurname(),
                    patronymic = user.getPatronymic();

            nameResponseDTO.setName(name);
            nameResponseDTO.setSurname(surname);
            nameResponseDTO.setPatronymic(patronymic);

            return nameResponseDTO;
        }
        else
            return null;

    }

    @Override
    public UserDTO setName(NameRequestDTO newNameDTO) {
        User user = userRepository.findUserByEmail(newNameDTO.getEmail());

        if(user!=null) {
            user.setName(newNameDTO.getName());
            user.setSurname(newNameDTO.getSurname());
            user.setPatronymic(newNameDTO.getPatronymic());

            User userUPD = userRepository.save(user);

            return  UserDTO.builder().name(userUPD.getName()).patronymic(userUPD.getPatronymic()).surname(userUPD.getSurname()).build();
        }
        else
            return null;
    }

    @Override
    public boolean ChangePassword(PasswordDTO passwordDTO) throws Exception {

        User user = userRepository.findUserByEmail(passwordDTO.getEmail());
        String currentPassword = user.getPassword();
        String oldPassword = passwordDTO.getOldPassword();

        if (bCryptPasswordEncoder.matches(oldPassword,currentPassword) ){
            if (passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
                String newPassword = bCryptPasswordEncoder.encode(passwordDTO.getNewPassword());
                user.setPassword(newPassword);

                userRepository.save(user);
            } else {

                throw new PasswordConfirmationException();
            }
        }
        else{
            throw new InvalidOldPasswordException();
        }

        return true;
    }
}
