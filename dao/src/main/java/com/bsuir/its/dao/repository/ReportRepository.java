package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,String> {
    @Query(value = "select r from Report r where r.reporterId = ?1")
    List<Report> findReportsByReporterId(String id);
    Report findById(String id);
    Report findByTitle(String title);
}
