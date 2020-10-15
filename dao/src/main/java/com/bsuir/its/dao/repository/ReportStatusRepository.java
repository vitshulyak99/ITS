package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ReportStatusRepository extends JpaRepository<ReportStatus,String> {
    ReportStatus findById(String id);
}
