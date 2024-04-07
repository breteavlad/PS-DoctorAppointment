/**
 * The DoctorSubject interface defines the contract for subjects that manage doctor entities
 * and notify observers of changes to these entities.
 */
package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Doctor;

public interface DoctorSubject {
    void registerObserver(DoctorObserver observer);
    void removeObserver(DoctorObserver observer);
    void notifyObservers(Doctor doctor, String action);
}
