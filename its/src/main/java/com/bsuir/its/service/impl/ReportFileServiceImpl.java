package com.bsuir.its.service.impl;

import com.bsuir.its.dao.model.ReportFile;
import com.bsuir.its.dao.model.enums.ReportFileType;
import com.bsuir.its.dao.repository.ReportFileRepository;
import com.bsuir.its.service.ReportFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportFileServiceImpl implements ReportFileService {
    private final ReportFileRepository reportFileRepository;

    @Autowired
    public ReportFileServiceImpl(ReportFileRepository reportFileRepository) {
        this.reportFileRepository = reportFileRepository;
    }


    @Override
    public String addFile(String path, ReportFileType type) {
        ReportFile reportFile = new ReportFile(type,path,null);

        ReportFile savedReportFile = reportFileRepository.save(reportFile);

        return savedReportFile.getId();
    }

    @Override
    public ReportFile get(String id) {
        return reportFileRepository.findReportFileById(id);
    }

    @Override
    public void delete(String id) {
        reportFileRepository.delete(id);
    }
}
