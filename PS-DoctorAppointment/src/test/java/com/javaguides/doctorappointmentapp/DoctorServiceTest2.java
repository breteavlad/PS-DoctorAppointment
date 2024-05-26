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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
        doctorService.registerObserver(doctorObserver); // Ensure observer is registered
    }

    /**
     * Test case to verify the updateDoctor method of DoctorService.
     * It verifies that the service successfully updates a doctor's information.
     */
    @Test
    void testUpdateDoctor() {
        long doctorId = 2L;
        Doctor existingDoctor = new Doctor();
        existingDoctor.setId(doctorId);
        existingDoctor.setName("Existing Name");
        existingDoctor.setSpecialty("Existing Specialty");

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(existingDoctor));

        Doctor updatedDoctorData = new Doctor();
        updatedDoctorData.setId(doctorId);
        updatedDoctorData.setName("Updated Name");
        updatedDoctorData.setSpecialty("Updated Specialty");

        when(doctorRepository.save(any(Doctor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Doctor returnedDoctor = doctorService.updateDoctor(doctorId, updatedDoctorData);

        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(1)).save(any(Doctor.class));

        assertNotNull(returnedDoctor, "Updated doctor should not be null");
        assertEquals(updatedDoctorData.getName(), returnedDoctor.getName());
        assertEquals(updatedDoctorData.getSpecialty(), returnedDoctor.getSpecialty());

        verify(doctorObserver, times(1)).update(any(Doctor.class), eq("updated"));
    }
}
