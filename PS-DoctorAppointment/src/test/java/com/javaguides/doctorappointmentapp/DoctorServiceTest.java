/**
 * Unit tests for the {@link DoctorService} class, focusing on the addDoctor method.
 * This class contains test cases for adding doctors using the {@link DoctorService}.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

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
     * Test case to verify the addDoctor method of DoctorService.
     * It verifies that the service successfully adds a doctor and notifies the observer.
     */
    @Test
    void testAddDoctor() {

        Doctor doctor = new Doctor();
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        doctorService.registerObserver(doctorObserver);


        Doctor result = doctorService.addDoctor(doctor);


        assertEquals(doctor, result);
        verify(doctorObserver, times(1)).update(doctor, "added");
        verify(doctorRepository).save(doctor);
    }


}
