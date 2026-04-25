package org.buildwithraghu.lowleveldesign.EHCHospital.entities;

import lombok.Data;

import java.util.Date;
import java.util.Random;

@Data
public class Patient {

    private String name;

    private Long id;

    private String primarySymptoms; // List of symptoms

    private Date lastModifiedDate;

    public Patient() {
        this.id = new Random().nextLong(0, 10000000L);
        this.lastModifiedDate = new Date();
    }
}
