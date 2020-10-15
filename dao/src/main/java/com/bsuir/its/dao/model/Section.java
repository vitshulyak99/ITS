package com.bsuir.its.dao.model;

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
@Table(name = "section")
public class Section extends BaseModel {

    @Column(name = "name",unique = true)
    private String name;

    @OneToMany(mappedBy = "section")
    List<Report> reports;

    @OneToMany(mappedBy = "section")
    List<Direction> directions;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "user_section",
            joinColumns =  {@JoinColumn(name = "section_id",referencedColumnName = "id")},
            inverseJoinColumns =  {@JoinColumn(name = "user_id",referencedColumnName = "id")})
    private List<User> users;
}
