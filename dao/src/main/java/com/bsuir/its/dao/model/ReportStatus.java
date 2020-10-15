package com.bsuir.its.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report_status")
public class ReportStatus extends BaseModel {

    @OneToOne
    User statusMaker;

    @ManyToOne
    Report report;

    @OneToOne
    Status status;

    @Column(name = "message")
    String message;
}
