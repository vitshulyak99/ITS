package com.bsuir.its.service.impl;

import com.bsuir.its.dao.model.*;
import com.bsuir.its.dao.repository.ReportRepository;
import com.bsuir.its.dao.repository.SectionRepository;
import com.bsuir.its.dao.repository.UserRepository;
import com.bsuir.its.dto.ReportDTO;
import com.bsuir.its.service.ReportFileService;
import com.bsuir.its.service.ReportService;
import com.bsuir.its.service.exception.ReportByIdNotFoundException;
import com.bsuir.its.service.exception.SectionByIdNotFound;
import com.bsuir.its.service.exception.UserByIdNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;
    private final ReportFileService reportFileService;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository,
                             SectionRepository sectionRepository,
                             UserRepository userRepository,
                             ReportFileService reportFileService) {
        this.reportRepository = reportRepository;
        this.sectionRepository = sectionRepository;
        this.userRepository = userRepository;
        this.reportFileService = reportFileService;
    }


    private static ReportDTO getReportDTO(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setTitle(report.getTitle());
        reportDTO.setId(report.getId());
        reportDTO.setSectionId(report.getSection().getId());
        reportDTO.setSectionId("На рассмотрении");
        return reportDTO;
    }

    @Override
    public String AddReport(String userId, String sectionId, String title) throws Exception {

        User user = userRepository.findUserById(userId);
        Section section = sectionRepository.findSectionById(sectionId);

        if(user==null){
            throw new UserByIdNotFound();
        }

        if(section==null){
            throw new SectionByIdNotFound();
        }

        Report report = new Report();
        report.setSection(section);
        report.setReporterId(user.getId());
        report.setTitle(title);

        report = reportRepository.save(report);
        return report.getId();
    }

    @Override
    public Report findById(String id) {

        return reportRepository.findById(id);
    }

    @Override
    public Report addFileToReport(String reportId, String reportFileId) throws ReportByIdNotFoundException {
        Report report = reportRepository.findById(reportId);

        if(report!=null){
            List<ReportFile> reportFiles = report.getReportFiles();

            if(reportFiles==null || reportFiles.isEmpty()){
                reportFiles = new ArrayList<>();
            }

            ReportFile reportFile = reportFileService.get(reportFileId);
            reportFile.setReport(report);

            reportFiles.add(reportFile);
            report.setReportFiles(reportFiles);

            return reportRepository.save(report);
        }else
        {
            throw new ReportByIdNotFoundException();
        }
    }

    @Override
    public void delete(String id) {
        reportRepository.delete(id);
    }

    @Override
    public Report edit(ReportDTO report) throws ReportByIdNotFoundException {

        Report oldReport = reportRepository.findById(report.getId());

        if(oldReport==null){
            throw new ReportByIdNotFoundException();
        }

        oldReport.setTitle(report.getTitle());

        return reportRepository.save(oldReport);

    }

    @Override
    public List<ReportDTO> getByReporterId(String userId) {
        List<ReportDTO> list = new ArrayList<>();
        for (Report report : reportRepository.findReportsByReporterId(userId)) {
            ReportDTO reportDTO = getReportDTO(report);
            list.add(reportDTO);
        }
        return list;
    }

    @Override
    public List<ReportDTO> getBydSectionIdFromReviewer(String userId) {

        List<Section> sections = userRepository.findUserById(userId).getSections();
        List<ReportDTO> reportDTOS = new ArrayList<>();
        sections.forEach(section -> {
            reportDTOS.addAll( section.getReports().stream().map(ReportServiceImpl::getReportDTO).collect(Collectors.toList()));

        });

        return reportDTOS;
    }

    @Override
    public List<ReportDTO> getBydSection(String sectionId) {
        Section section = sectionRepository.findSectionById(sectionId);
        List<Report> reports =  section.getReports();

        return reports.stream().map(ReportServiceImpl::getReportDTO).collect(Collectors.toList());
    }
}
