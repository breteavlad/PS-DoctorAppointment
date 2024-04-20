package com.javaguides.doctorappointmentapp.repository;

import com.javaguides.doctorappointmentapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
