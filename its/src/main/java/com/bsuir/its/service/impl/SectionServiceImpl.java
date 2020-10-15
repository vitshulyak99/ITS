package com.bsuir.its.service.impl;

import com.bsuir.its.dao.model.Section;
import com.bsuir.its.dao.model.User;
import com.bsuir.its.dao.repository.SectionRepository;
import com.bsuir.its.dao.repository.UserRepository;
import com.bsuir.its.dto.DirectionDTO;
import com.bsuir.its.dto.SectionDTO;
import com.bsuir.its.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SectionServiceImpl(SectionRepository sectionRepository, UserRepository userRepository) {
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SectionDTO> getSections() {
        return sectionRepository
                .findAll()
                .stream()
                .map(section ->
                        new SectionDTO(
                                section.getId(),
                                section.getName(),
                                section.getDirections().stream().map(direction -> new DirectionDTO(direction.getName())).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }


    @Override
    public List<SectionDTO> setSections(String userId, List<String> sectionIds) {
        List<Section> sections = new ArrayList<>();

        sectionIds.forEach(s -> {
            Section section =  sectionRepository.findSectionById(s);
            sections.add(section);
        });

        User user = userRepository.findUserById(userId);
        List<Section> existSections =  user.getSections();

        if(existSections!=null && !existSections.isEmpty())
        {
            sections.addAll(existSections);
        }
        user.setSections(sections);

        try {
            userRepository.save(user);
        }
        catch (Exception e){
            System.out.println("\n\n\n" + e.getMessage() + "\n\n\n");

            throw e;
        }

        return sections.stream().map(section ->
                new SectionDTO(section.getId(),section.getName(),null))
                .collect(Collectors.toList());

    }
}
