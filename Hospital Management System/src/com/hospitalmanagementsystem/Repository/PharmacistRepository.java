package com.hospitalmanagementsystem.Repository;

import com.hospitalmanagementsystem.Model.Pharmacist;
import java.util.ArrayList;
import java.util.List;

public class PharmacistRepository {
    private final List<Pharmacist> pharmacists = new ArrayList<>();

    public void addPharmacist(Pharmacist pharmacist) {
        pharmacists.add(pharmacist);
    }

    public Pharmacist findPharmacistById(String id) {
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getId().equals(id)) {
                return pharmacist;
            }
        }
        return null;
    }

    public List<Pharmacist> getAllPharmacists() {
        return pharmacists;
    }
}
