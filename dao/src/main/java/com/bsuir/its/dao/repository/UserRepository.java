package com.bsuir.its.dao.repository;


import com.bsuir.its.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {

    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);

    User findUserById(String id);

    User deleteUserById(String id);
}



