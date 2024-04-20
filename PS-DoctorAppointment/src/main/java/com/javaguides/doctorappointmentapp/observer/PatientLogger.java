package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientLogger implements PatientObserver {

    @Override
    public void update(Patient patient, String action) {
        // Log the doctor changes
        System.out.println("Patient " + action + ": " + patient);
    }
}