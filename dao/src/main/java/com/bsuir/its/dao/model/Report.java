package com.bsuir.its.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reports")
public class Report extends BaseModel{
    @Column(name = "title",unique = true)
    private String title;

    @Column(name = "reporter_id")
    private String reporterId;

    @ManyToOne
    private Section section;

    @OneToMany(mappedBy = "report",fetch = FetchType.LAZY)
    private List<ReportStatus> reportStatuses;

    @OneToOne
    private Status edit_state;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "report")
    private List<ReportFile> reportFiles;
}
