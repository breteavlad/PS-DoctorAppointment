package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.observer.DoctorObserver;
import com.javaguides.doctorappointmentapp.repository.ContactInformationRepository;
import com.javaguides.doctorappointmentapp.repository.DoctorRepository;
import com.javaguides.doctorappointmentapp.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link DoctorService} class, focusing on the deleteDoctor method.
 * This class contains test cases for deleting doctors using the {@link DoctorService}.
 * It utilizes Mockito for mocking dependencies and verifying the behavior of the service method.
 */
public class DoctorServiceTest3 {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private ContactInformationRepository contactInformationRepository;

    @Mock
    private DoctorObserver doctorObserver;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorService.registerObserver(doctorObserver); // Ensure observer is registered
    }

    @Test
    void testDeleteDoctor() {
        // Create a mock doctor
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        // Mock the behavior of doctorRepository.findById to return the mock doctor
        when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));

        // Call the deleteDoctor method
        doctorService.deleteDoctor(doctor.getId());

        // Verify that the doctorRepository.findById method was called once with the correct doctor ID
        verify(doctorRepository, times(1)).findById(doctor.getId());

        // Verify that the doctorRepository.deleteById method was called once with the correct doctor ID
        verify(doctorRepository, times(1)).deleteById(doctor.getId());

        // Verify that doctorObserver.update(doctor, "deleted") was called once
        verify(doctorObserver, times(1)).update(doctor, "deleted");
    }
}
