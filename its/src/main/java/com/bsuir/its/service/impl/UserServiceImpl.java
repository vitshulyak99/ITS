package com.bsuir.its.service.impl;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.model.User;
import com.bsuir.its.dao.repository.RoleRepository;
import com.bsuir.its.dao.repository.UserRepository;
import com.bsuir.its.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${app.default.admin.email}")
    private String email;

    @Value("${app.default.admin.password}")
    private String password;

    @Value("${app.default.admin.name}")
    private String name;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("USER");
        Date now = new Date();

        Role roleConferee = roleRepository.findByName("CONFEREE");

        List<Role> userRoles = user.getRoles();

        if(userRoles == null || userRoles.isEmpty()) {
            userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            userRoles.add(roleConferee);
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setCreated(now);

        //        log.info("IN register - user: {} successfully registered", registeredUser);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
//        log.info("IN getAll - {} users found", result.size());

        return result;
    }

    @Override
    public User findById(String id) {
        User result = userRepository.findUserById(id);

//        if(result == null) {
////            log.warn(" IN findById - no user found by id: {}", id);
//        }else {
////            log.info("IN findById - user: {} found by id: {}", result, id);
//        }

        return result;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }


    @Override
    public void delete(String id) {
        User deletedUser = userRepository.deleteUserById(id);
//        log.info("IN delete - user: {} was deleted by id: {}",deletedUser,id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        User user = userRepository.findUserByEmail(email);

        if(user==null){
            Date now = new Date();
            User admin = new User();
            admin.setPassword(passwordEncoder.encode(password));
            admin.setName(name);
            admin.setSurname(name);
            admin.setPatronymic(name);
            admin.setEmail(email);
            admin.setCreated(now);
            admin.setActive(true);
            List<Role> roles = new ArrayList<>();
            Role roleAdmin = roleRepository.findByName("ADMIN");
            Role roleUser = roleRepository.findByName("USER");
            roles.add(roleAdmin);
            roles.add(roleUser);
            admin.setRoles(roles);

            userRepository.save(admin);
        }
    }
}
