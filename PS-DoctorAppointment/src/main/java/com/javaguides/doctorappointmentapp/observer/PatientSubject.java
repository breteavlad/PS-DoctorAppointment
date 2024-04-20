package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.model.Patient;

public interface PatientSubject {
    void registerObserver(PatientObserver observer);
    void removeObserver(PatientObserver observer);
    void notifyObservers(Patient doctor, String action);
}
