package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.Patient;
import com.javaguides.doctorappointmentapp.observer.PatientObserver;
import com.javaguides.doctorappointmentapp.repository.ContactInformationClientRepository;
import com.javaguides.doctorappointmentapp.repository.ContactInformationRepository;
import com.javaguides.doctorappointmentapp.repository.PatientRepository;
import com.javaguides.doctorappointmentapp.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * Unit tests for the {@link PatientService} class, focusing on the deletePatient method.
 * This class contains test cases for deleting patients using the {@link PatientService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service method.
 */
public class PatientServiceTest3 {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ContactInformationClientRepository contactInformationClientRepository;

    @Mock
    private PatientObserver patientObserver;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        patientService.registerObserver(patientObserver); // Ensure observer is registered
    }

    /**
     * Test case to verify the deletePatient method of PatientService.
     * It verifies that the service successfully deletes a patient and notifies the observer.
     */
    @Test
    void testDeletePatient() {
        // Create a mock patient
        Patient patient = new Patient();
        patient.setId(2L);

        // Mock the behavior of patientRepository.findById to return the mock patient
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        // Call the deletePatient method
        patientService.deletePatient(patient.getId());

        // Verify that the patientRepository.findById method was called once with the correct patient ID
        verify(patientRepository, times(1)).findById(patient.getId());

        // Verify that the patientRepository.deleteById method was called once with the correct patient ID
        verify(patientRepository, times(1)).deleteById(patient.getId());

        // Verify that patientObserver.update(patient, "deleted") was called once
        verify(patientObserver, times(1)).update(patient, "deleted");

        // Mock the behavior of patientRepository.findById to return an empty Optional
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());

        // Call the deletePatient method again with a non-existing patient ID
        patientService.deletePatient(patient.getId());

        // Verify that the patientRepository.findById method was called twice in total
        verify(patientRepository, times(2)).findById(patient.getId());

        // Verify that patientObserver.update was never called again
        verify(patientObserver, times(1)).update(patient, "deleted"); // No additional updates should be called
    }
}
