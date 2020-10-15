package com.bsuir.its.dao.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
public class BaseModel {

    @Id
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    @NotEmpty
    @NotNull
    private String id = UUID.randomUUID().toString();

    @CreatedDate
    @Column(name = "created", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}


