package com.bsuir.its.service;

import com.bsuir.its.dto.SectionDTO;

import java.util.List;

public interface SectionService {
    List<SectionDTO> setSections(String userId, List<String> sectionIds);
    List<SectionDTO> getSections();
}
