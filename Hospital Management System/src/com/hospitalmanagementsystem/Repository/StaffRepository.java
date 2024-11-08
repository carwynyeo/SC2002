package com.hospitalmanagementsystem.Repository;


//Fit all the Staff here
public class StaffRepository {
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private PharmacistRepository pharmacistRepository;

    public StaffRepository(DoctorRepository doctorRepository, PatientRepository patientRepository, PharmacistRepository pharmacistRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.pharmacistRepository = pharmacistRepository;


    }
}
