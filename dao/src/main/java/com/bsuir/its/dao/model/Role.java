package com.bsuir.its.dao.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Builder
public class Role extends BaseModel{

    @Column(name = "name",unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }
}
