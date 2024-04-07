/**
 * AppointmentLogger class implements the AppointmentObserver interface and serves as an observer
 * to log changes to appointments.
 */
package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentLogger implements AppointmentObserver {

    @Override
    public void update(Appointment appointment, String action) {
        // Log the appointment changes
        System.out.println("Appointment " + action + ": " + appointment);
    }
}
