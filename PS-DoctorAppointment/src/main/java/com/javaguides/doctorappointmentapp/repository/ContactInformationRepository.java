package com.javaguides.doctorappointmentapp.repository;

import com.javaguides.doctorappointmentapp.model.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {
    // You can add custom methods here if needed
}