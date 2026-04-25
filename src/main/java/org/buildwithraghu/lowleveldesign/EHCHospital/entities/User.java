package org.buildwithraghu.lowleveldesign.EHCHospital.entities;

public class User {

    private String name;

    private UserType type;

    private Long hospitalId;

    public User() {}

    public User(String name, UserType type, Long hospitalId) {
        this.name = name;
        this.type = type;
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }
}