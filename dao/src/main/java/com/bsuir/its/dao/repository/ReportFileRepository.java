package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.ReportFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportFileRepository extends JpaRepository<ReportFile,String> {
    ReportFile findReportFileById(String id);
}
