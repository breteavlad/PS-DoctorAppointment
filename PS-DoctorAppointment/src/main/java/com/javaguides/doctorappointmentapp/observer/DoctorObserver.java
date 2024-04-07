package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Doctor;

/**
 * The DoctorObserver interface defines the contract for classes that observe changes to doctor entities.
 * Observers implementing this interface can receive updates from the subject when changes occur.
 */
public interface DoctorObserver {
    void update(Doctor doctor, String action);
}
