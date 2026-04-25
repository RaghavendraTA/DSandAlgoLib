package org.buildwithraghu.lowleveldesign.EHCHospital;

import org.buildwithraghu.lowleveldesign.EHCHospital.entities.Hospital;
import org.buildwithraghu.lowleveldesign.EHCHospital.entities.Patient;
import org.buildwithraghu.lowleveldesign.EHCHospital.entities.User;

public class ActionService {

    private EHCRepo ehcRepo = EHCRepo.getInstant();

    public void Upsert(Patient patient) {
        ehcRepo.save(patient);
    }

    public Patient getPatientById(Long id) {
        return ehcRepo.fetchPatientById(id);
    }

    public Patient searchPatient(String name) {
        return ehcRepo.searchPatient(name).orElse(null);
    }

    public User login(String username) {
        return ehcRepo.login(username).orElse(null);
    }

    public Hospital getHospitalById(Long id) {
        return ehcRepo.fetchHospitalById(id);
    }

    public void updatePatient(Long id, String newSymptoms) {
        ehcRepo.updatePatient(id, newSymptoms);
    }
}
