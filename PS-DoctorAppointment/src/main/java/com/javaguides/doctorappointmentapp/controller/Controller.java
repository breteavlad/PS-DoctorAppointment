package com.javaguides.doctorappointmentapp.controller;

import com.javaguides.doctorappointmentapp.model.Appointment;
import com.javaguides.doctorappointmentapp.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class Controller {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update an existing appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        return appointmentRepository.findById(id)
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
    }
    // Delete an appointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        return appointmentRepository.findById(id)
                .map(appointment -> {
                    appointmentRepository.delete(appointment);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}