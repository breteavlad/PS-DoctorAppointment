/**
 * The DoctorLogger class is an observer that logs changes to doctor entities.
 * It implements the DoctorObserver interface to receive updates from the subject.
 */
package com.javaguides.doctorappointmentapp.observer;

import com.javaguides.doctorappointmentapp.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorLogger implements DoctorObserver {

    @Override
    public void update(Doctor doctor, String action) {
        // Log the doctor changes
        System.out.println("Doctor " + action + ": " + doctor);
    }
}
