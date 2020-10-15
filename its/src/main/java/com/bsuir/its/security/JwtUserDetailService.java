package com.bsuir.its.security;

import com.bsuir.its.dao.model.User;
import com.bsuir.its.security.jwt.JwtUser;
import com.bsuir.its.security.jwt.JwtUserFactory;
import com.bsuir.its.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    /***
     * @param username is email
     * @return UserDetails
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  userService.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User with username: "
                    + username
                    + "not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
//        log.info("IN loadUserByUsername - user with username: {} successfully loaded",username);

        return jwtUser;
    }
}
