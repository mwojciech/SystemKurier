package com.prz.systemkurier.enumerate;

public enum RoleName {

    KURIER("KURIER"),
    ADMIN("ADMIN");

    private String roleName;

    RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }
}
