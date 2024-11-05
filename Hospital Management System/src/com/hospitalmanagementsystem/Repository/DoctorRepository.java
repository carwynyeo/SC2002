package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }


    public Doctor findDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }
}

