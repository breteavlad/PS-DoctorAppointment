package com.javaguides.doctorappointmentapp;

import com.javaguides.doctorappointmentapp.model.Appointment;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Long id = 1L;
        String patientName = "John Doe";
        String doctorName = "Dr. Smith";
        LocalDate date = LocalDate.of(2024, 4, 10);
        LocalTime hour = LocalTime.of(10, 30);
        String description = "Regular checkup";

        // Act
        Appointment appointment = new Appointment();
        appointment.setId(id);
        appointment.setPatientName(patientName);
        appointment.setDoctorName(doctorName);
        appointment.setDate(date);
        appointment.setHour(hour);
        appointment.setDescription(description);

        // Assert
        assertEquals(id, appointment.getId());
        assertEquals(patientName, appointment.getPatientName());
        assertEquals(doctorName, appointment.getDoctorName());
        assertEquals(date, appointment.getDate());
        assertEquals(hour, appointment.getHour());
        assertEquals(description, appointment.getDescription());
    }

    @Test
    public void testToString() {
        // Arrange
        Long id = 1L;
        String patientName = "John Doe";
        String doctorName = "Dr. Smith";
        LocalDate date = LocalDate.of(2024, 4, 10);
        LocalTime hour = LocalTime.of(10, 30);
        String description = "Regular checkup";
        String expectedToString = "Appointment{id=1, patientName='John Doe', doctorName='Dr. Smith', date=2024-04-10, hour=10:30, description='Regular checkup'}";

        // Act
        Appointment appointment = new Appointment(id, patientName, doctorName, date, hour, description);

        // Assert
        assertEquals(expectedToString, appointment.toString());
    }
}
