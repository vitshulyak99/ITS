package com.bsuir.its.service;

import com.bsuir.its.dao.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findById(String id);

    User findByEmail(String email);

    User update(User user);

    void delete(String id);
}