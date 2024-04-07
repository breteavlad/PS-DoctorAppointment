/**
 * Controller class defines REST endpoints for managing appointments.
 * This class handles HTTP requests related to appointments such as getting all appointments,
 * adding a new appointment, updating an existing appointment, and deleting an appointment.
 */
package com.javaguides.doctorappointmentapp.controller;

import com.javaguides.doctorappointmentapp.model.Appointment;
import com.javaguides.doctorappointmentapp.observer.AppointmentLogger;
import com.javaguides.doctorappointmentapp.service.AppointmentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class Controller {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentLogger appointmentLogger; // Example observer

    /**
     * Initializes the Controller by registering the AppointmentLogger as an observer
     * to the AppointmentService.
     */
    @PostConstruct
    public void init() {
        appointmentService.registerObserver(appointmentLogger);
    }
    /**
     * Retrieves all appointments.
     * @return List of all appointments.
     */
    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    /**
     * Adds a new appointment.
     * @param appointment The appointment to be added.
     * @return The added appointment.
     */
    // Add a new appointment
    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }
    /**
     * Updates an existing appointment.
     * @param id The ID of the appointment to be updated.
     * @param updatedAppointment The updated appointment information.
     * @return The updated appointment.
     */
    // Update an existing appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        return appointmentService.updateAppointment(id, updatedAppointment);
    }
    /**
     * Deletes an appointment by its ID.
     * @param id The ID of the appointment to be deleted.
     * @return ResponseEntity indicating the success of the operation.
     */
    // Delete an appointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
