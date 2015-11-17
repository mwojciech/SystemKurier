package com.prz.kuriertrack.Model;


import java.util.Date;

public class Status {

    private Long id;
    private String type;
    private String name;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCurrent_location() {
        return current_location;
    }
    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }

    public Date getStatus_date() {
        return status_date;
    }
    public void setStatus_date(Date status_date) {
        this.status_date = status_date;
    }

    private Date status_date;
    private String current_location;

}
