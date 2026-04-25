package org.buildwithraghu.lowleveldesign.EHCHospital.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;

    private UserType type;

    private Long hospitalId;
}
