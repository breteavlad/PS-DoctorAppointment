package com.javaguides.doctorappointmentapp.repository;

import com.javaguides.doctorappointmentapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // You can define additional methods here if needed
}
