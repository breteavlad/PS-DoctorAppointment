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
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AppointmentService} class, focusing on the updateAppointment method.
 * This class contains test cases for updating appointments using the {@link AppointmentService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service method.
 */

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
    }
    /**
     * Test case to verify the updateAppointment method of AppointmentService.
     * It verifies that the service updates an existing appointment with new data.
     */

    @Test
    void testUpdateAppointment() {

        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);


        when(appointmentRepository.findById(existingAppointment.getId())).thenReturn(Optional.of(existingAppointment));


        Appointment updatedAppointmentData = new Appointment();
        updatedAppointmentData.setDate(LocalDate.of(2024,12,24));


        Appointment updatedAppointment = appointmentService.updateAppointment(existingAppointment.getId(), updatedAppointmentData);


        assertEquals(updatedAppointmentData.getDate(), updatedAppointment.getDate());


        verify(appointmentRepository, times(1)).findById(existingAppointment.getId());


        verify(appointmentRepository, times(1)).save(updatedAppointment);


        verify(appointmentObserver, times(1)).update(updatedAppointment, "updated");
    }
}
