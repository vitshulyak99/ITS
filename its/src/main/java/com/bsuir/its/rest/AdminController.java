package com.bsuir.its.rest;

import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.model.User;
import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.UserDTO;
import com.bsuir.its.dto.admin.AdminCreateUserDTO;
import com.bsuir.its.dto.requests.ChangeRoleDTO;
import com.bsuir.its.dto.responses.GetUsersDTO;
import com.bsuir.its.service.AdminService;
import com.bsuir.its.service.RoleService;
import com.bsuir.its.service.UserService;
import com.bsuir.its.service.mailService.MailService;
import com.bsuir.its.service.password.PassGenerator;
import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;
    private final PassGenerator passGenerator;
    private final MailService mailService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(AdminService adminService,
                           PassGenerator passGenerator,
                           MailService mailService,
                           UserService userService,
                           RoleService roleService) {
        this.adminService = adminService;
        this.passGenerator = passGenerator;
        this.mailService = mailService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public ResponseEntity getUsers(){
        List<UserDTO> userDTOList = adminService.getUsers();
        List<RoleDTO> roleDTOList = adminService.getRoles();
        return ResponseEntity.ok( new GetUsersDTO(userDTOList,roleDTOList));
    }

    @RequestMapping(method = RequestMethod.POST,value = "changeRole")
    public ResponseEntity updateRoles(@RequestBody ChangeRoleDTO changeRoleDTO){
        UserDTO userDTO = adminService.updateUserRoles(changeRoleDTO.getId(),changeRoleDTO.getRoles());

        return ResponseEntity.ok(userDTO);
    }


    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public  ResponseEntity createUser(@RequestBody AdminCreateUserDTO createUserDTO){

        String password = passGenerator.generate(12);

        List<Role> roles =Arrays.stream(createUserDTO.getRoles())
                .map(roleDTO ->
                        {
                            Role role = new Role();
                            role.setId(roleDTO.getId());

                            return role;
                        }
                ).collect(Collectors.toList());


        User user = userService.register(
                User.builder()
                        .email(createUserDTO.getEmail())
                        .password(password)
                        .roles(
                                roles
                        ).build()
        );
        mailService.sendSimpleMessage(user.getEmail(),"Доступ к сайту конференции ITS" ,"Email: " + user.getEmail() + "\nPassword: " + password);


        return ResponseEntity.ok("Success!");
    }

}
