package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.Section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepository extends JpaRepository<Section,String> {
    Section findSectionById(String id);
    Section findSectionByName(String name);
}
