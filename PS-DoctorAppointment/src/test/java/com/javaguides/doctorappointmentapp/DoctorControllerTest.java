package com.javaguides.doctorappointmentapp;



import com.javaguides.doctorappointmentapp.controller.DoctorController;
import com.javaguides.doctorappointmentapp.model.ContactInformation;
import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.model.DoctorRequest;
import com.javaguides.doctorappointmentapp.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDoctors() {
        // Arrange
        List<Doctor> doctors = new ArrayList<>();
        when(doctorService.getAllDoctors()).thenReturn(doctors);

        // Act
        List<Doctor> result = doctorController.getAllDoctors();

        // Assert
        assertEquals(doctors, result);
    }

    @Test
    void testAddDoctor() {
        // Arrange
        DoctorRequest doctorRequest = new DoctorRequest("John Doe", "Cardiologist", "john@example.com", "1234567890", "123 Main St","8AM-17PM","john24","21");
        Doctor doctor = new Doctor(1L, "John Doe", "Cardiologist", new ContactInformation( "john@example.com", "1234567890", "123 Main St"), "");
        when(doctorService.addDoctor(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor result = doctorController.addDoctor(doctorRequest);

        // Assert
        assertEquals(doctor, result);
    }

    @Test
    void testUpdateDoctor() {
        // Arrange
        Long id = 1L;
        Doctor updatedDoctor = new Doctor();
        when(doctorService.updateDoctor(id, updatedDoctor)).thenReturn(updatedDoctor);

        // Act
        Doctor result = doctorController.updateDoctor(id, updatedDoctor);

        // Assert
        assertEquals(updatedDoctor, result);
    }

    @Test
    void testDeleteDoctor() {
        // Arrange
        Long id = 1L;
        ResponseEntity<?> expectedResponse = ResponseEntity.ok().build();

        // Act
        ResponseEntity<?> result = doctorController.deleteDoctor(id);

        // Assert
        assertEquals(expectedResponse, result);
        verify(doctorService, times(1)).deleteDoctor(id);
    }
}
