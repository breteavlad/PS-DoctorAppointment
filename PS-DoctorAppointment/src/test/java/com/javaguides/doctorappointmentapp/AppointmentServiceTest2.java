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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest2 {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AppointmentObserver appointmentObserver;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appointmentService.registerObserver(appointmentObserver); // Ensure observer is registered
    }

    @Test
    void testUpdateAppointment() {
        // Create existing appointment
        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);

        // Mock repository to return existing appointment
        when(appointmentRepository.findById(existingAppointment.getId())).thenReturn(Optional.of(existingAppointment));

        // Create updated appointment data
        Appointment updatedAppointmentData = new Appointment();
        updatedAppointmentData.setId(1L);
        updatedAppointmentData.setDate(LocalDate.of(2024, 12, 24));

        // Mock repository save behavior
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Update appointment
        Appointment updatedAppointment = appointmentService.updateAppointment(existingAppointment.getId(), updatedAppointmentData);

        // Verify that the appointment was updated correctly
        assertNotNull(updatedAppointment, "Updated appointment should not be null");
        assertEquals(updatedAppointmentData.getDate(), updatedAppointment.getDate(), "Dates should match");

        // Verify interactions with the repository and observer
        verify(appointmentRepository, times(1)).findById(existingAppointment.getId());
        verify(appointmentRepository, times(1)).save(existingAppointment);
        verify(appointmentObserver, times(1)).update(any(Appointment.class), eq("updated"));
    }
}
