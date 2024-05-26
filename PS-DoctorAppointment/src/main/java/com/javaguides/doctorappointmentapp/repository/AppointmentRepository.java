package com.javaguides.doctorappointmentapp.repository;


import com.javaguides.doctorappointmentapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Long>  {
    // This will automatically provide basic CRUD operations on Appointment entities


}
