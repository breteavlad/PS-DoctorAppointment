
/**
 * Unit tests for the {@link PatientService} class, focusing on the addPatient method.
 * This class contains test cases for adding patients using the {@link PatientService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service method.
 */
package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.Patient;
import com.javaguides.doctorappointmentapp.observer.DoctorObserver;
import com.javaguides.doctorappointmentapp.observer.PatientObserver;
import com.javaguides.doctorappointmentapp.repository.ContactInformationRepository;
import com.javaguides.doctorappointmentapp.repository.PatientRepository;
import com.javaguides.doctorappointmentapp.service.DoctorService;
import com.javaguides.doctorappointmentapp.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ContactInformationRepository contactInformationRepository;

    @Mock
    private PatientObserver patientObserver;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case to verify the addPatient method of PatientService.
     * It verifies that the service adds a new patient and notifies the observer.
     */
    @Test
    void testAddPatient() {
        // Create a mock patient
        Patient patient = new Patient();

        // Mock the behavior of patientRepository.save(patient)
        when(patientRepository.save(patient)).thenReturn(patient);
        System.out.println("Existing patient: " + patient);

        // Register the patientObserver with patientService
        patientService.registerObserver(patientObserver);

        // Call the method under test
        Patient result = patientService.addPatient(patient);

        // Verify that the patientRepository.save(patient) method was called once
        verify(patientRepository, Mockito.times(1)).save(patient);

        // Verify that patientObserver.update(patient, "added") was called once
        verify(patientObserver, Mockito.times(1)).update(patient, "added");

        // Assert that the returned result is the same as the mock patient
        assertEquals(patient, result);
    }









}
