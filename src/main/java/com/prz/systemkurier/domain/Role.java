package com.prz.systemkurier.domain;

import com.prz.systemkurier.enumerate.RoleName;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "ROLE")
public class Role {

    @Id
    @SequenceGenerator(name = "ROLE_ID_GENERATOR", sequenceName = "ROLE_ID_SEQ")
    @GeneratedValue(generator = "ROLE_ID_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_NAME")
    private RoleName name;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role(RoleName roleName, Date createDate){
        this.name = roleName;
        this.createDate = createDate;
    }

    public Role(){

    }

 }
