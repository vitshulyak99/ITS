package com.bsuir.its.rest;

import com.bsuir.its.common.MediaTypeUtils;
import com.bsuir.its.dao.model.Archive;
import com.bsuir.its.dao.repository.ArchiveRepository;
import com.bsuir.its.dto.ArchiveDTO;
import com.bsuir.its.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArchiveController  {

    private final FileService fileService;
    private final ArchiveRepository archiveRepository;
    private final ServletContext servletContext;


    public ArchiveController(FileService fileService, ArchiveRepository archiveRepository, ServletContext servletContext) {
        this.fileService = fileService;
        this.archiveRepository = archiveRepository;
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "admin/uploadCollection",method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam("file") MultipartFile file,
                                 @RequestParam("year") Integer year,
                                 @RequestParam("description") String description) throws IOException {

        String filename = fileService.SaveToArchive(file);
        Archive archive = archiveRepository.save(Archive.builder().description(description).filename(filename).year(year).build());


        return  ResponseEntity.ok(ArchiveDTO.builder().description(archive.getDescription()).id(archive.getId()).year(archive.getYear()).build());
    }

    @RequestMapping(method = RequestMethod.POST ,value = "public/archive/download")
    public ResponseEntity download(@RequestParam("id") String id) throws IOException {

        Archive archive = archiveRepository.findById(id);

        File file = fileService.GetFromArchive(archive.getFilename());

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

    @RequestMapping(method = RequestMethod.GET, value = "public/archive/all")
    public ResponseEntity<List<ArchiveDTO>> all(){
        return ResponseEntity.ok(archiveRepository
                .findAll()
                .stream()
                .map(archive -> ArchiveDTO.builder().id(archive.getId()).description(archive.getDescription()).year(archive.getYear()).build())
                .collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "admin/removeCollection")
    public ResponseEntity remove(@RequestParam("id") String id){
        archiveRepository.delete(id);
        return  ResponseEntity.ok(id);
    }
}
