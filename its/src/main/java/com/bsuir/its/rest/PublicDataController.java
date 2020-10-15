package com.bsuir.its.rest;

import com.bsuir.its.dto.DegreeDTO;
import com.bsuir.its.dto.RoleDTO;
import com.bsuir.its.service.DegreeService;
import com.bsuir.its.service.RoleService;
import com.bsuir.its.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "public/api/data")
public class PublicDataController {

    private final SectionService sectionService;
    private final DegreeService degreeService;
    private final RoleService roleService;

    @Autowired
    public PublicDataController(SectionService sectionService, DegreeService degreeService, RoleService roleService) {
        this.sectionService = sectionService;
        this.degreeService = degreeService;
        this.roleService = roleService;
    }
    @RequestMapping(value = "allSections",method = RequestMethod.GET)
    public ResponseEntity allSections(){

        return ResponseEntity.ok(sectionService
                .getSections());

    }
    @RequestMapping(value = "allDegrees",method = RequestMethod.GET)
    public ResponseEntity allDegree(){
        return ResponseEntity.ok(degreeService.getAll()
                .stream()
                .map(degree -> new DegreeDTO(degree.getId(),degree.getName()))
                .collect(Collectors.toList()));
    }
    @RequestMapping(value = "allRoles",method = RequestMethod.GET)
    public ResponseEntity allRoles(){
        return ResponseEntity.ok(roleService
                .getAll()
                .stream()
                .map(role -> new RoleDTO(role.getId(),role.getName()))
                .collect(Collectors.toList())
        );
    }
}
