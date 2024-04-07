/**
 * The AppointmentService class provides functionalities to manage appointments
 * and notifies observers of changes to these appointments.
 */
package com.javaguides.doctorappointmentapp.service;

import com.javaguides.doctorappointmentapp.model.Appointment;
import com.javaguides.doctorappointmentapp.observer.AppointmentObserver;
import com.javaguides.doctorappointmentapp.observer.AppointmentSubject;
import com.javaguides.doctorappointmentapp.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements AppointmentSubject {

    private List<AppointmentObserver> observers = new ArrayList<>();

    @Autowired
    private AppointmentRepository appointmentRepository;



    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment addAppointment(Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        notifyObservers(savedAppointment, "added");
        return savedAppointment;
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment savedAppointment = appointmentRepository.findById(id)
                .map(appointment -> {
                    appointment.setPatientName(updatedAppointment.getPatientName());
                    appointment.setDoctorName(updatedAppointment.getDoctorName());
                    appointment.setDate(updatedAppointment.getDate());
                    appointment.setHour(updatedAppointment.getHour());
                    appointment.setDescription(updatedAppointment.getDescription());
                    return appointmentRepository.save(appointment);
                })
                .orElseGet(() -> {
                    updatedAppointment.setId(id);
                    return appointmentRepository.save(updatedAppointment);
                });
        notifyObservers(savedAppointment, "updated");
        return savedAppointment;
    }

    public void deleteAppointment(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        appointmentOptional.ifPresent(appointment -> {
            appointmentRepository.deleteById(id);
            notifyObservers(appointment, "deleted");
        });
    }

    @Override
    public void registerObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AppointmentObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Appointment appointment, String action) {
        for (AppointmentObserver observer : observers) {
            observer.update(appointment, action);
        }
    }
}
