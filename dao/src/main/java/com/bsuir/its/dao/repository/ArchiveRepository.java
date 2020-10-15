package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive,String> {
    Archive findByYear(int year);
    Archive findById(String id);
}
