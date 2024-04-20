package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.Appointment;
import com.javaguides.doctorappointmentapp.observer.AppointmentObserver;
import com.javaguides.doctorappointmentapp.repository.AppointmentRepository;
import com.javaguides.doctorappointmentapp.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AppointmentService} class, focusing on the deleteAppointment method.
 * This class contains test cases for deleting appointments using the {@link AppointmentService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service method.
 */
public class AppointmentServiceTest3 {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentObserver appointmentObserver;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case to verify the deleteAppointment method of AppointmentService.
     * It verifies that the service deletes an existing appointment by ID.
     */

    @Test
    void testDeleteAppointment() {
        // Create a mock appointment
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        // Mock the behavior of appointmentRepository.findById to return the mock appointment
        when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.of(appointment));


        appointmentService.deleteAppointment(appointment.getId());

        // Verify that the appointmentRepository.findById method was called once with the correct appointment ID
        verify(appointmentRepository, times(1)).findById(appointment.getId());

        // Verify that the appointmentRepository.deleteById method was called once with the correct appointment ID
        verify(appointmentRepository, times(1)).deleteById(appointment.getId());

        // Verify that appointmentObserver.update(appointment, "deleted") was called once
        verify(appointmentObserver, times(1)).update(appointment, "deleted");
    }
}
