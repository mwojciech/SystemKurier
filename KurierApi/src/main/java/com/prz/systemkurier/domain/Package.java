package com.prz.systemkurier.domain;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "PACKAGE")
public class Package {

    @Id
    @GeneratedValue(generator = "packageId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "packageId", sequenceName = "PACKAGE_ID_SEQ")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "id")
    private Location location;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
}
