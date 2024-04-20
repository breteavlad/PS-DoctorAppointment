/**
 * Unit tests for the {@link DoctorService} class, focusing on the deleteDoctor method.
 * This class contains test cases for deleting doctors using the {@link DoctorService}.
 * It utilizes Mockito for mocking dependencies and verifying the behavior of the service method.
 */
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
/**
 * Test case to verify the deleteDoctor method of DoctorService.
 * It ensures that the service successfully deletes a doctor from the system.
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
    }

    @Test
    void testDeleteDoctor() {

        Doctor doctor = new Doctor();
        doctor.setId(1L);


        when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));


        doctorService.deleteDoctor(doctor.getId());


        verify(doctorRepository, times(1)).findById(doctor.getId());


        verify(doctorRepository, times(1)).deleteById(doctor.getId());


        verify(doctorObserver, times(1)).update(doctor, "deleted");
    }
}
