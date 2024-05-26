/**
 * Unit tests for the {@link PatientService} class, focusing on the updatePatient method.
 * This class contains test cases for updating patient information using the {@link PatientService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service method.
 */
package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.ContactInformationClient;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Test case to verify the updatePatient method of PatientService.
 * It verifies that the service updates an existing patient and saves the changes.
 */
public class PatientServiceTest2 {
    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ContactInformationClientRepository contactInformationClientRepository;

    @Mock
    private PatientObserver patientObserver;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdatePatient() {
        // Create a mock patient
        Patient existingPatient = new Patient();
        existingPatient.setId(14L);
        existingPatient.setPatientName("Initial Name");
        existingPatient.setDiseaseDescription("Initial Disease");

        // Mock the behavior of patientRepository.findById(existingPatient.getId())
        when(patientRepository.findById(existingPatient.getId())).thenReturn(Optional.of(existingPatient));

        // Mock the behavior of patientRepository.save(updatedPatient)
        when(patientRepository.save(Mockito.any(Patient.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Register the patientObserver with patientService
        patientService.registerObserver(patientObserver);

        // Call the method under test
        Patient updatedPatient = patientService.updatePatient(existingPatient.getId(), existingPatient);

        // Verify that the patientRepository.findById(existingPatient.getId()) method was called once
        verify(patientRepository, Mockito.times(1)).findById(existingPatient.getId());

        // Verify that the patientRepository.save(updatedPatient) method was called once
        verify(patientRepository, Mockito.times(1)).save(existingPatient);

        // Verify that patientObserver.update(patient, "updated") was called once
        verify(patientObserver, Mockito.times(1)).update(existingPatient, "updated");

        // Assert that the returned updatedPatient is not null
        assertNotNull(updatedPatient);

        // Assert that the updated patient data matches the expected values
        assertEquals(existingPatient.getPatientName(), updatedPatient.getPatientName());
        assertEquals(existingPatient.getDiseaseDescription(), updatedPatient.getDiseaseDescription());
    }


}
