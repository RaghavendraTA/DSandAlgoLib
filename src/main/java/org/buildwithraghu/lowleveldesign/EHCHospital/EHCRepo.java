package org.buildwithraghu.lowleveldesign.EHCHospital;

import org.buildwithraghu.lowleveldesign.EHCHospital.entities.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public final class EHCRepo {

    private static EHCRepo singleton;
    private final static Random random = new Random();

    private final HashMap<Long, Patient> patientsRecords = new HashMap<>();
    private final HashMap<Long, User> usersRecords = new HashMap<>();
    private final HashMap<Long, Hospital> hospitalRecords = new HashMap<>();

    private EHCRepo() {
        hospitalRecords.put(123L, new Hospital("Cerner", 1234L));
        usersRecords.put(random.nextLong(), new User("Raghu", UserType.DOCTOR, 123L));
        usersRecords.put(random.nextLong(), new User("Aishwarya", UserType.NURSE, 123L));
        usersRecords.put(random.nextLong(), new User("Rajendra", UserType.CARE_GIVER, 123L));
    }

    public static EHCRepo getInstant() {
        if (singleton == null) {
            singleton = new EHCRepo();
        }
        return singleton;
    }

    public Patient save(Patient patient) {
        patientsRecords.put(patient.getId(), patient);
        return patient;
    }

    public Patient fetchPatientById(Long id) {
        return patientsRecords.get(id);
    }

    public Optional<Patient> searchPatient(String search) {
        return patientsRecords.values()
                .stream()
                .filter(x -> x.getName().startsWith(search))
                .findFirst();
    }

    public Optional<User> login(String username) {
        return usersRecords.values()
                .stream()
                .filter(x -> x.getName().equals(username))
                .findFirst();
    }

    public Hospital fetchHospitalById(Long id) {
        return hospitalRecords.getOrDefault(id, null);
    }

    public void updatePatient(Long id, String primarySymptoms) {
        Patient patient  = patientsRecords.get(id);
        if (patient == null) return;
        patient.setPrimarySymptoms(primarySymptoms);
        patient.setLastModifiedDate(new Date());
    }
}
