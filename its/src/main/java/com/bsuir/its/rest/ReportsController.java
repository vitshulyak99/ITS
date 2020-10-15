package com.bsuir.its.rest;


import com.bsuir.its.dao.model.enums.ReportFileType;
import com.bsuir.its.dto.ReportDTO;
import com.bsuir.its.dto.requests.ChoiceSection;
import com.bsuir.its.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.*;

@RestController
@RequestMapping(value = "public")
public class ReportsController {

    private final SectionService sectionService;
    private final FileService fileService;
    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final UserService userService;
    private ServletContext servletContext;

    @Autowired
    public ReportsController(SectionService sectionService,
                             FileService fileService,
                             ReportService reportService,
                             ReportFileService reportFileService,
                             UserService userService,
                             ServletContext servletContext) {
        this.sectionService = sectionService;
        this.fileService = fileService;
        this.reportService = reportService;
        this.reportFileService = reportFileService;
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "conferee/choiceSection", method = RequestMethod.POST)
    public ResponseEntity choiceSection(@RequestBody ChoiceSection choiceSection) {
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("sections",sectionService
                .setSections(choiceSection.getUserId(),
                        choiceSection.getSectionIds()));
        return ResponseEntity.ok(hashMap);
    }
    @RequestMapping(value = "conferee/getSections", method = RequestMethod.GET)
    public ResponseEntity choiceSection() {
        HashMap<Object,Object> hashMap = new HashMap<>();
        hashMap.put("sections",sectionService.getSections());
        return ResponseEntity.ok(hashMap);
    }
    @RequestMapping(value = "conferee/upload",method = RequestMethod.POST)
    public ResponseEntity reportUpload(
            @RequestParam("userId") String userId,
            @RequestParam("sectionId") String sectionId,
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("title") String title){
        try {
            String reportId = reportService.AddReport(userId,sectionId,title);

            for (MultipartFile file:
                 files) {
                String path = fileService.SaveFile(file);
                String reportFileId = reportFileService.addFile(path, ReportFileType.PDF);
                reportService.addFileToReport(reportId,reportFileId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Ok!");
    }
    @RequestMapping(value = "conferee/getReports",method = RequestMethod.GET)
    public ResponseEntity getReports(@RequestParam("userId") String userId){

        List<ReportDTO> reportDTOS = reportService.getByReporterId(userId);
        return ResponseEntity.ok(reportDTOS );
    }
    @RequestMapping(value = "reviewer/getReports",method = RequestMethod.POST)
    public ResponseEntity getReportsReviewer(@RequestPart("userId") String userId){
        List<ReportDTO> reportDTOS = reportService.getBydSectionIdFromReviewer(userId);
        return ResponseEntity.ok(reportDTOS);
    }


}

