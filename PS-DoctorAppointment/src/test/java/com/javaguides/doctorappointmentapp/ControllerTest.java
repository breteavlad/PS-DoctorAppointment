package com.javaguides.doctorappointmentapp;



import com.javaguides.doctorappointmentapp.controller.Controller;
import com.javaguides.doctorappointmentapp.model.Appointment;
import com.javaguides.doctorappointmentapp.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAppointments() {
        // Arrange
        List<Appointment> appointments = new ArrayList<>();
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        // Act
        List<Appointment> result = controller.getAllAppointments();

        // Assert
        assertEquals(appointments, result);
    }

    @Test
    void testAddAppointment() {
        // Arrange
        Appointment appointment = new Appointment();
        when(appointmentService.addAppointment(appointment)).thenReturn(appointment);

        // Act
        Appointment result = controller.addAppointment(appointment);

        // Assert
        assertEquals(appointment, result);
    }

    @Test
    void testUpdateAppointment() {
        // Arrange
        Long id = 1L;
        Appointment updatedAppointment = new Appointment();
        when(appointmentService.updateAppointment(id, updatedAppointment)).thenReturn(updatedAppointment);

        // Act
        Appointment result = controller.updateAppointment(id, updatedAppointment);

        // Assert
        assertEquals(updatedAppointment, result);
    }

    @Test
    void testDeleteAppointment() {
        // Arrange
        Long id = 1L;
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        // Act
        ResponseEntity<?> result = controller.deleteAppointment(id);

        // Assert
        assertEquals(expectedResponse, result);
        verify(appointmentService, times(1)).deleteAppointment(id);
    }
}

