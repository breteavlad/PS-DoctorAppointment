/**
 * The AppointmentObserver interface represents an observer that is notified of changes to appointments.
 * Implementations of this interface can receive updates about appointment modifications.
 */
package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Appointment;

public interface AppointmentObserver {
    void update(Appointment appointment, String action);
}

