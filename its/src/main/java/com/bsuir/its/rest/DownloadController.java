package com.bsuir.its.rest;

import com.bsuir.its.common.MediaTypeUtils;
import com.bsuir.its.dao.model.Report;
import com.bsuir.its.dao.model.ReportFile;
import com.bsuir.its.dao.model.enums.ReportFileType;
import com.bsuir.its.dao.repository.SectionRepository;
import com.bsuir.its.service.FileService;
import com.bsuir.its.service.ReportService;
import com.bsuir.its.service.mailService.MailService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DownloadController  {

    private final ReportService reportService;
    private final FileService fileService;
    private final ServletContext servletContext;
    private final SectionRepository sectionRepository;
    private final MailService mailService;

    public DownloadController(ReportService reportService, FileService fileService, ServletContext servletContext, SectionRepository sectionRepository, MailService mailService) {
        this.reportService = reportService;
        this.fileService = fileService;
        this.servletContext = servletContext;
        this.sectionRepository = sectionRepository;
        this.mailService = mailService;
    }

    @RequestMapping(value = "download",method = RequestMethod.GET)
    public ResponseEntity download(@RequestParam(name = "reportId") String reportId, @RequestParam("reportFileType") String reportFileType) {
        Report report =  reportService.findById(reportId);
        List<ReportFile> reportFiles = report.getReportFiles();
        Optional<ReportFile> reportFileOptional =  reportFiles.stream().filter(r->r.getFileType().toString().equals(reportFileType)).findFirst();

        if(!reportFileOptional.isPresent())
            return null;

        ReportFile reportFile =reportFileOptional.get();
        String pathToFile = reportFile.getPathToFile();
        File file = fileService.GetFile(pathToFile);
        Path path = Paths.get(file.getAbsolutePath());
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, path.getFileName().toString());
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(mediaType).contentLength(file.length())
                .contentLength(file.length())
                .body(resource);
    }


    @RequestMapping(value = "download/s",method = RequestMethod.GET)
    public ResponseEntity download() {


        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, "file.pdf");
        InputStreamResource resource = null;
        File file= new File("C:\\itsupload\\file.pdf");
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(mediaType).contentLength(file.length())
                .contentLength(file.length())
                .body(resource);
    }


    @RequestMapping(method = RequestMethod.GET , value = "send")
    public ResponseEntity send(@RequestParam("message") String message){

        mailService.sendSimpleMessage("ivan@ico.com","Работа одобрена","Тема:Решение задачи классификации. \nСтатус: Одобрена. ");

        return  ResponseEntity.ok("ok");
    }

    @RequestMapping(value = "redactor/download",method = RequestMethod.GET)
    public ResponseEntity getZip(@RequestParam(name = "sectionId") String id) {
        List<ReportFile> reportFiles = new ArrayList<>();
        sectionRepository.findSectionById(id).getReports().forEach(report -> reportFiles.addAll(report.getReportFiles()));
        List<String> paths = reportFiles
                .stream()
                .filter(reportFile -> reportFile.getFileType().equals(ReportFileType.ZIP))
                .map(ReportFile::getPathToFile)
                .collect(Collectors.toList());

        File file = null;

        try {
            file = fileService.GetZipFiles(paths);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert file != null;
        Path path = Paths.get(file.getAbsolutePath());

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, path.getFileName().toString());
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(mediaType).contentLength(file.length())
                .contentLength(file.length())
                .body(resource);

    }

    @RequestMapping(value = "chair/download",method = RequestMethod.GET)
    public ResponseEntity download(@RequestParam(name = "sectionId") String id) {
        List<ReportFile> reportFiles = new ArrayList<>();
        sectionRepository.findSectionById(id).getReports().forEach(report -> reportFiles.addAll(report.getReportFiles()));
        List<String> paths = reportFiles
                .stream()
                .filter(reportFile -> reportFile.getFileType().equals(ReportFileType.PDF))
                .map(ReportFile::getPathToFile)
                .collect(Collectors.toList());
        File file = null;
        try {
            file = fileService.GetZipFiles(paths);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert file != null;
        Path path = Paths.get(file.getAbsolutePath());

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, path.getFileName().toString());
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName())
                .contentType(mediaType).contentLength(file.length())
                .contentLength(file.length())
                .body(resource);

    }
}
