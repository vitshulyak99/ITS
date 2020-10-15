package com.bsuir.its.service.impl;

import com.bsuir.its.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class FileServiceImpl  implements FileService {
    @Value("${app.upload.directory}")
    String directory;
    @Value("${app.upload.temp}")
    String tmpDirectory;
    @Value("${app.upload.archive}")
    String archive;
    @Override
    public String SaveFile(MultipartFile file) throws IOException {

        Path filepath = Paths.get(directory,file.getOriginalFilename());

        try(OutputStream os = Files.newOutputStream(filepath)){

            os.write(file.getBytes());

        }catch (Exception ex){
            throw  ex;
        }

        return file.getOriginalFilename();
    }

    @Override
    public File GetFile(String path) {

        return new File(path);
    }

    @Override
    public File GetZipFiles(List<String> paths) throws IOException {

        String zipPath = UUID.randomUUID()+".zip";
        try(FileOutputStream fileOutputStream = new FileOutputStream(zipPath);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            for (String path : paths
                 ) {
                Path path_ = Paths.get(path);


                try(FileInputStream fileInputStream = new FileInputStream(path)){
                    ZipEntry entry = new ZipEntry(path_.getFileName().toString());
                    zipOutputStream.putNextEntry(entry);

                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    zipOutputStream.write(buffer);
                    zipOutputStream.closeEntry();

                }catch (IOException e) {

                    System.out.println(e.getMessage());
                    e.printStackTrace();

                    throw e;
                }
            }

        } catch (IOException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

            throw e;
        }


        return new File(zipPath);
    }

    @Override
    public String SaveToArchive(MultipartFile file) throws IOException {
        Path filepath = Paths.get(archive,file.getOriginalFilename());

        try(OutputStream os = Files.newOutputStream(filepath)){

            os.write(file.getBytes());

        }catch (Exception ex){
            throw  ex;
        }

        return  file.getOriginalFilename();
    }

    @Override
    public File GetFromArchive(String filename) throws IOException {
        Path filepath = Paths.get(archive,filename);

        return  new File(filepath.toString());
    }

    void clearTempDirectory(){

    }
}
