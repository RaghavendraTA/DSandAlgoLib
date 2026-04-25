package org.buildwithraghu.lowleveldesign.EHCHospital;

import org.buildwithraghu.lowleveldesign.EHCHospital.entities.*;

import java.util.Scanner;
import java.util.Set;

public class EHCMain {

    private final static Set<Role> CREATEROLES = Set.of(Role.CRU, Role.CRUD);

    Scanner scanner = new Scanner(System.in);
    ActionService service;

    public EHCMain() {
        service = new ActionService();
    }

    public Patient createPatient() {
        Patient patient = new Patient();
        System.out.print("Enter Patient Name: ");
        patient.setName(scanner.next());
        System.out.print("Add primary symptom: ");
        patient.setPrimarySymptoms(scanner.next());
        service.Upsert(patient);
        return patient;
    }

    public Patient getPatientUsingId() {
        long id = scanner.nextLong();
        service.getPatientById(id);
        return null;
    }

    public Patient searchPatientByName() throws Exception {
        System.out.print("Enter patient name: ");
        String search = scanner.next();
        Patient patient = service.searchPatient(search);
        if (patient == null) {
            throw new Exception("No User found with: " + search);
        }
        return patient;
    }

    public void updatePatientInfo(Long id) {
        System.out.println("Enter new Symptomps details: ");
        String newSymp = scanner.next();
        service.updatePatient(id, newSymp);
        System.out.println("Update completed");
    }

    public void selectPatient(Patient patient, int option) {
        while(option > 1 && option <= 3) {
            System.out.println("\nSelected Patient: " + patient.getName());
            System.out.println("1. Update Patient symptoms: ");
            System.out.println("2. Show details");
            System.out.println("3. go back");
            int newOption = scanner.nextInt();
            switch (newOption) {
                case 1 -> updatePatientInfo(patient.getId());
                case 2 -> System.out.println(service.getPatientById(patient.getId()));
                default -> { return; }
            }
        }
    }

    public void start(User user, Hospital hospital) {
        while (true) {
            System.out.println("\n1. Get patient record using id");
            System.out.println("2. Search patient record");

            if (CREATEROLES.contains(hospital.getUserRoleMap().get(user.getType())))
                System.out.println("3. Create patient record");
            System.out.print("Pick an Option or anything else to quit: ");

            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 3 -> selectPatient(createPatient(), option);
                    case 1 -> selectPatient(getPatientUsingId(), option);
                    case 2 -> selectPatient(searchPatientByName(), option);
                    default -> {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        User user = service.login(username);
        if (user == null) {
            System.out.println("Invalid username");
        } else {
            Hospital hospital = service.getHospitalById(user.getHospitalId());
            System.out.println("Hi " + user.getName() + ", Logged in to " + hospital.getName());
            this.start(user, hospital);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the EHC system");
        new EHCMain().login();
    }
}
