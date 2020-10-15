package com.bsuir.its.dao.model;

import com.bsuir.its.dao.model.enums.ReportFileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report_files")
public class ReportFile extends BaseModel {

    @Column(name = "file_type")
    @Enumerated(EnumType.STRING)
    private ReportFileType fileType;

    @Column(name = "filename")
    private String pathToFile;

    @ManyToOne
    private Report report;
}
