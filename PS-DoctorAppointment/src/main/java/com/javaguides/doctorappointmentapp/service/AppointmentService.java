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

    private final List<AppointmentObserver> observers = new ArrayList<>();
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

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
        if (updatedAppointment == null) {
            throw new IllegalArgumentException("Updated appointment data cannot be null");
        }

        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment existingAppointment = optionalAppointment.get();
            System.out.println("Updating existing appointment with ID: " + id);
            existingAppointment.setPatientName(updatedAppointment.getPatientName());
            existingAppointment.setDoctorName(updatedAppointment.getDoctorName());
            existingAppointment.setDate(updatedAppointment.getDate());
            existingAppointment.setHour(updatedAppointment.getHour());
            existingAppointment.setDescription(updatedAppointment.getDescription());
            Appointment savedAppointment = appointmentRepository.save(existingAppointment);
            System.out.println("Updated appointment saved: " + savedAppointment);
            notifyObservers(savedAppointment, "updated");
            return savedAppointment;
        } else {
            System.out.println("Creating new appointment with ID: " + id);
            updatedAppointment.setId(id);
            Appointment savedAppointment = appointmentRepository.save(updatedAppointment);
            System.out.println("New appointment created: " + savedAppointment);
            notifyObservers(savedAppointment, "updated");
            return savedAppointment;
        }
    }






    public void deleteAppointment(Long id) {
        System.out.println("Attempting to delete appointment with ID: " + id);

        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            System.out.println("Appointment found: " + appointment);
            appointmentRepository.deleteById(id);
            System.out.println("Appointment deleted: " + appointment);
            notifyObservers(appointment, "deleted");
        } else {
            System.out.println("No appointment found with ID: " + id);
        }
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
