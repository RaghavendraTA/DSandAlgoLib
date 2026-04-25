package org.buildwithraghu.lowleveldesign.EHCHospital.entities;

import lombok.Data;

import java.util.Map;

@Data
public class Hospital {

    private String name;

    private Long registrationId;

    private Map<UserType, Role> userRoleMap;

    public Hospital(String name, Long registrationId) {
        this.name = name;
        this.registrationId = registrationId;
        this.userRoleMap = Map.of(
                UserType.DOCTOR, Role.CRUD,
                UserType.NURSE, Role.CRU,
                UserType.CARE_GIVER, Role.RU
        );
    }
}
