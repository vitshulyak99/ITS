package com.bsuir.its.security.jwt;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getPassword(),
                user.getEmail(),
                user.isActive(),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
                );
    }

    private static List<? extends GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        List<SimpleGrantedAuthority> result =
                userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());

        return result;
    }
}
