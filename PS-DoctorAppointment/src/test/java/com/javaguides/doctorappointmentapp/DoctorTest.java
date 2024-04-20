package com.javaguides.doctorappointmentapp;


import com.javaguides.doctorappointmentapp.model.ContactInformation;
import com.javaguides.doctorappointmentapp.model.Doctor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DoctorTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Long id = 1L;
        String name = "Dr. John";
        String specialty = "Cardiologist";
        ContactInformation contactInformation = new ContactInformation();
        String workingHours = "Monday - Friday, 9:00 AM - 5:00 PM";

        // Act
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setSpecialty(specialty);
        doctor.setContactInformation(contactInformation);
        doctor.setWorkingHours(workingHours);

        // Assert
        assertEquals(id, doctor.getId());
        assertEquals(name, doctor.getName());
        assertEquals(specialty, doctor.getSpecialty());
        assertEquals(contactInformation, doctor.getContactInformation());
        assertEquals(workingHours, doctor.getWorkingHours());
    }

    @Test
    public void testToString() {
        // Arrange
        Long id = 1L;
        String name = "Dr. John";
        String specialty = "Cardiologist";
        ContactInformation contactInformation = new ContactInformation();
        String workingHours = "Monday - Friday, 9:00 AM - 5:00 PM";
        String expectedToString = "Doctor{id=1, name='Dr. John', specialty='Cardiologist', contactInformation=" + contactInformation + ", workingHours='Monday - Friday, 9:00 AM - 5:00 PM'}";

        // Act
        Doctor doctor = new Doctor(id, name, specialty, contactInformation, workingHours);

        // Assert
        assertEquals(expectedToString, doctor.toString());
    }
}
