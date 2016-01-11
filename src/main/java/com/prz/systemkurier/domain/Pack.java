package com.prz.systemkurier.domain;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "PACKAGE")
public class Pack {

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "PACKAGE_ID_SEQ")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Column(name = "DESCRIPTION", nullable = false)
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

    @Column(name = "STREET")
    private String street;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "POSTAL_CODE")
    private String postal_code;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
