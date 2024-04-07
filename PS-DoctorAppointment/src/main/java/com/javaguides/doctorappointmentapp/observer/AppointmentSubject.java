/**
 * The AppointmentSubject interface represents a subject that manages appointment observers.
 * Classes implementing this interface can register, remove, and notify observers about appointment changes.
 */
package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Appointment;

public interface AppointmentSubject {
    void registerObserver(AppointmentObserver observer);
    void removeObserver(AppointmentObserver observer);
    void notifyObservers(Appointment appointment, String action);
}

