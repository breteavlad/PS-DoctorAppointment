package com.javaguides.doctorappointmentapp.repository;

import com.javaguides.doctorappointmentapp.model.ContactInformationClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationClientRepository extends JpaRepository<ContactInformationClient,Long> {
}
