package com.bsuir.its.dao.model;


import lombok.*;

import javax.persistence.*;
        import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User extends BaseModel {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "job_address")
    private String jobAddress;

    @Column(name="phone")
    private String phone;

    @Column(name = "country")
    String country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roles;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "user_section",
            joinColumns =  {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns =  {@JoinColumn(name = "section_id",referencedColumnName = "id")})
    private List<Section> sections;

    @OneToOne
    private Degree degree;
}
