package com.bsuir.its.service;



import com.bsuir.its.dao.model.ReportFile;
import com.bsuir.its.dao.model.enums.ReportFileType;

public interface ReportFileService {

    String addFile(String path, ReportFileType type);

    ReportFile get(String id);

    void delete(String id);

}
