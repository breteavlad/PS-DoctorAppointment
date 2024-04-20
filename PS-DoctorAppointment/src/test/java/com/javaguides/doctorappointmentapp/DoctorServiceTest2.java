/**
 * Unit tests for the {@link DoctorService} class, focusing on the updateDoctor method.
 * This class contains test cases for updating doctors using the {@link DoctorService}.
 * It uses Mockito for mocking dependencies and testing the behavior of the service method.
 */
package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.ContactInformation;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DoctorServiceTest2 {

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
    }
    /**
     * Test case to verify the updateDoctor method of DoctorService.
     * It verifies that the service successfully updates a doctor's information.
     */
    @Test
    void testUpdateDoctor() {

        Doctor existingDoctor = new Doctor();
        existingDoctor.setId(2L);


        when(doctorRepository.findById(existingDoctor.getId())).thenReturn(Optional.of(existingDoctor));


        Doctor updatedDoctorData = new Doctor();
        updatedDoctorData.setName("Updated Name");
        updatedDoctorData.setSpecialty("Updated Specialization");


        Doctor updatedDoctor = doctorService.updateDoctor(existingDoctor.getId(), updatedDoctorData);


        assertEquals(updatedDoctorData, updatedDoctor);


        verify(doctorRepository, times(1)).findById(existingDoctor.getId());


        verify(doctorRepository, times(1)).save(updatedDoctor);


        assertEquals(updatedDoctorData.getName(), updatedDoctor.getName());
        assertEquals(updatedDoctorData.getSpecialty(), updatedDoctor.getSpecialty());
    }
}
