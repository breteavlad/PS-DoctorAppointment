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

        Patient existingPatient = new Patient();
        existingPatient.setId(2L);
        existingPatient.setPatientName("Initial Name");
        existingPatient.setDiseaseDescription("Initial Disease");


        when(patientRepository.findById(existingPatient.getId())).thenReturn(Optional.of(existingPatient));


        Patient updatedPatientData = new Patient();
        updatedPatientData.setId(existingPatient.getId());
        updatedPatientData.setPatientName("Updated Name");
        updatedPatientData.setDiseaseDescription("Updated Disease");


        ContactInformationClient contactInformationClient = new ContactInformationClient();
        contactInformationClient.setEmail("example@yahoo.com");
        contactInformationClient.setPhone("07535234241");
        contactInformationClient.setAddress("exampleaddress");
        updatedPatientData.setContactInformationClient(contactInformationClient);

        // Call the method under test
        Patient updatedPatient = patientService.updatePatient(existingPatient.getId(), updatedPatientData);


        assertNotNull(updatedPatient);


        verify(patientRepository, Mockito.times(1)).findById(existingPatient.getId());


        verify(patientRepository, Mockito.times(1)).save(updatedPatient);


        assertEquals(updatedPatientData.getPatientName(), updatedPatient.getPatientName());
        assertEquals(updatedPatientData.getDiseaseDescription(), updatedPatient.getDiseaseDescription());
    }

}
