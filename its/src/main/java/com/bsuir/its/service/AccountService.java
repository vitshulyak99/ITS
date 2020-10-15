package com.bsuir.its.service;

import com.bsuir.its.dto.UserDTO;
import com.bsuir.its.dto.requests.NameRequestDTO;
import com.bsuir.its.dto.responses.NameResponseDTO;
import com.bsuir.its.dto.requests.PasswordDTO;
import com.bsuir.its.service.exception.InvalidOldPasswordException;

import java.util.UUID;

public interface AccountService {
    NameResponseDTO getName(String id);
    UserDTO setName(NameRequestDTO newNameDTO);
    boolean ChangePassword(PasswordDTO passwordDTO) throws InvalidOldPasswordException, Exception;

}
