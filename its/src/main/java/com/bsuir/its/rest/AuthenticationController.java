package com.bsuir.its.rest;

import com.bsuir.its.convertors.RegistrationRequestDTOConverter;
import com.bsuir.its.exception.PasswordConfirmationException;
import com.bsuir.its.exception.UserWithEmailIsExistException;
import com.bsuir.its.dao.model.Degree;
import com.bsuir.its.dao.model.Role;
import com.bsuir.its.dao.model.User;
import com.bsuir.its.dao.repository.DegreeRepository;
import com.bsuir.its.dao.repository.SectionRepository;
import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.dto.SectionDTO;
import com.bsuir.its.dto.requests.LoginRequestDTO;
import com.bsuir.its.dto.requests.RegistrationRequestDTO;
import com.bsuir.its.dto.responses.LoginResponseDTO;
import com.bsuir.its.dto.responses.RegistrationResponseDTO;
import com.bsuir.its.security.jwt.JwtTokenProvider;
import com.bsuir.its.service.SectionService;
import com.bsuir.its.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/public/authentication/")
public class AuthenticationController {

    public final AuthenticationManager authenticationManager;
    public final JwtTokenProvider jwtTokenProvider;
    public final UserService userService;
    public final SectionRepository sectionRepository;
    public final DegreeRepository degreeRepository;
    public final SectionService sectionService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService, SectionRepository sectionRepository, DegreeRepository degreeRepository, SectionService sectionService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.sectionRepository = sectionRepository;
        this.degreeRepository = degreeRepository;
        this.sectionService = sectionService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        try {
            String emailAsUsername = loginRequestDTO.getEmail();

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            emailAsUsername,
                            loginRequestDTO
                                    .getPassword()));
            User user = userService.findByEmail(emailAsUsername);
            List<Role> roles = user.getRoles();
            String token = jwtTokenProvider.createToken(emailAsUsername, roles);

            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setId(user.getId());
            responseDTO.setEmail(emailAsUsername);
            responseDTO.setToken(token);
            responseDTO.setRoles(roles.stream().map(role -> RoleDTO.builder().name(role.getName()).id(role.getId()).build()).collect(Collectors.toList()));
            responseDTO.setSections(user.getSections().stream().map(section -> new SectionDTO(section.getId(), section.getName(),null)).collect(Collectors.toList()));
            responseDTO.setFio(
                    user.getName()
                    + " "
                    + user.getSurname()
                    + " "
                    + user.getPatronymic()
            );

            return ResponseEntity.ok(responseDTO);

        } catch (AuthenticationException e) {

            throw new BadCredentialsException("Incorrect password or email");
        }

    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ResponseEntity registry(
            @RequestBody
                    RegistrationRequestDTO registrationRequestDTO)
            throws
            UserWithEmailIsExistException,
            PasswordConfirmationException {
        if (registrationRequestDTO.getPassword().equals(registrationRequestDTO.getConfirmPassword())) {
            User newUser = RegistrationRequestDTOConverter.convert(registrationRequestDTO);
            Degree degree = degreeRepository.findById(registrationRequestDTO.getDegreeId());

            if (degree == null) {
                degree = new Degree("Отсутствует");
                degree = degreeRepository.save(degree);
            }

            newUser.setDegree(degree);

            User userByEmail = userService.findByEmail(registrationRequestDTO.getEmail());

            if (userByEmail == null) {

                User registeredUser;
                registeredUser = userService.register(newUser);
                registeredUser.setSections(registrationRequestDTO
                        .getSectionsIds()
                        .stream()
                        .map(sectionRepository::findSectionById)
                        .collect(Collectors.toList()));
                registeredUser = userService.update(registeredUser);
                RegistrationResponseDTO responseDTO = new RegistrationResponseDTO();
                responseDTO.setEmail(registeredUser.getEmail());
                responseDTO.setId(registeredUser.getId());

                return ResponseEntity.ok(responseDTO);
            } else {
                throw new UserWithEmailIsExistException();
            }
        } else {
            throw new PasswordConfirmationException();
        }
    }

    @RequestMapping(value = "existToken",method = RequestMethod.GET)
    public ResponseEntity restoreUser(HttpServletRequest request){
        String token = request.getHeader("Authorization").split("_")[1];
        String email = jwtTokenProvider.getEmail(token);
        User user = userService.findByEmail(email);

        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("id",user.getId());
        hashMap.put("fio",
                user.getName()
                        + ' '
                        + user.getSurname()
                        + ' '
                        + user.getPatronymic()
        );
        hashMap.put("email",user.getEmail());
        hashMap.put("sections",
                user.getSections()
                .stream()
                .map(section ->
                new SectionDTO(
                        section.getId(),
                        section.getName(),null)
                ).collect(Collectors.toList())
        );
        hashMap.put("roles",
                user.getRoles()
                .stream()
                .map(role -> new RoleDTO(role.getId(),role.getName()))
                .collect(Collectors.toList())
        );

        return ResponseEntity.ok(hashMap);

    }
}
