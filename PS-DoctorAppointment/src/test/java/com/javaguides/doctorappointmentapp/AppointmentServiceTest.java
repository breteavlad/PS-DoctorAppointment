/**
 * Unit tests for the {@link AppointmentService} class.
 * This class contains test cases for the functionality provided by the {@link AppointmentService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service methods.
 */
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentObserver appointmentObserver;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /**
     * Test case to verify the getAllAppointments method of AppointmentService.
     * It verifies that the service returns a list of all appointments from the repository.
     */
    @Test
    public void testGetAllAppointments() {
        // Arrange
        List<Appointment> appointments = new ArrayList<>();
        when(appointmentRepository.findAll()).thenReturn(appointments);

        // Act
        List<Appointment> result = appointmentService.getAllAppointments();

        // Assert
        assertEquals(appointments, result);
    }

    /**
     * Test case to verify the addAppointment method of AppointmentService.
     * It verifies that the service adds an appointment to the repository and notifies the observer.
     */
    @Test
    public void testAddAppointment() {

        Appointment appointment = new Appointment();
        when(appointmentRepository.save(appointment)).thenReturn(appointment);


        appointmentService.registerObserver(appointmentObserver);


        Appointment result = appointmentService.addAppointment(appointment);


        assertEquals(appointment, result);
        verify(appointmentObserver, times(1)).update(appointment, "added");
    }



}

