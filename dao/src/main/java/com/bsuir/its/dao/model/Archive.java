package com.bsuir.its.dao.model;

import com.sun.xml.internal.ws.server.ServerRtException;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "archive")
public class Archive extends  BaseModel {

    @Column(name = "year")
    private int year;
    @Column(name = "filename")
    private String filename;

    @Column(name = "description")
    private String description;
}
