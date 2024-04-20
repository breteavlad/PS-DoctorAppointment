package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.model.Patient;

public interface PatientObserver {
    void update(Patient patient, String action);
}
