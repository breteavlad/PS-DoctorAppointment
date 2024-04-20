package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.Patient;
import com.javaguides.doctorappointmentapp.observer.PatientObserver;
import com.javaguides.doctorappointmentapp.repository.ContactInformationRepository;
import com.javaguides.doctorappointmentapp.repository.PatientRepository;
import com.javaguides.doctorappointmentapp.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private ContactInformationRepository contactInformationRepository;

    @Mock
    private PatientObserver patientObserver;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    /**
     * Test case to verify the deletePatient method of PatientService.
     * It verifies that the service successfully deletes a patient and notifies the observer.
     */
    @Test
    void testDeletePatient() {

        Patient patient = new Patient();
        patient.setId(2L);


        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));


        patientService.deletePatient(patient.getId());


        verify(patientRepository, times(1)).findById(patient.getId());


        verify(patientRepository, times(1)).deleteById(patient.getId());


        verify(patientObserver, times(1)).update(patient, "deleted");


        reset(patientObserver);


        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());
        patientService.deletePatient(patient.getId());


        verify(patientRepository, times(2)).findById(patient.getId());


        verifyNoInteractions(patientObserver);
    }


}