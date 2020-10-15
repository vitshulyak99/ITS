package com.bsuir.its.service;

import com.bsuir.its.dao.model.Report;

import com.bsuir.its.dto.ReportDTO;
import com.bsuir.its.service.exception.ReportByIdNotFoundException;
import com.bsuir.its.service.exception.UserByIdNotFound;

import java.util.List;


public interface ReportService {
    String AddReport(String userId, String sectionId, String title) throws UserByIdNotFound, Exception;
    Report findById(String id);
    Report addFileToReport(String reportId, String reportFileId) throws ReportByIdNotFoundException;
    void delete(String id);
    Report edit(ReportDTO report) throws ReportByIdNotFoundException;
    List<ReportDTO> getByReporterId(String userId);
    List<ReportDTO> getBydSectionIdFromReviewer(String userId);
    List<ReportDTO> getBydSection(String sectionId);

}

