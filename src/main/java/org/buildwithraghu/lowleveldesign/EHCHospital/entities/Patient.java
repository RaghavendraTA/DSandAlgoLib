package org.buildwithraghu.lowleveldesign.EHCHospital.entities;

import java.util.Date;
import java.util.Random;

public class Patient {

    private String name;

    private Long id;

    private String primarySymptoms;

    private Date lastModifiedDate;

    public Patient() {
        this.id = Math.abs(new Random().nextLong()) % 10000000L;
        this.lastModifiedDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimarySymptoms() {
        return primarySymptoms;
    }

    public void setPrimarySymptoms(String primarySymptoms) {
        this.primarySymptoms = primarySymptoms;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}