package com.bsuir.its.service;


import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    String SaveFile(MultipartFile file) throws IOException;
    File GetFile(String filename);
    File GetZipFiles(List<String> paths) throws IOException;
    String SaveToArchive(MultipartFile file) throws IOException;
    File GetFromArchive(String filename) throws IOException;
}
