package com.prz.systemkurier.enumerate;

public enum Status {
    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED"),
    SUSPENDED("SUSPENDED");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
