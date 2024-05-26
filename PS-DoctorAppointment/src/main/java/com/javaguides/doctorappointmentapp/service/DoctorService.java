/**
 * The DoctorService class provides functionalities to manage doctors
 * and notifies observers of changes to these doctors.
 */
package com.javaguides.doctorappointmentapp.service;

import com.javaguides.doctorappointmentapp.model.ContactInformation;
import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.observer.DoctorObserver;
import com.javaguides.doctorappointmentapp.observer.DoctorSubject;
import com.javaguides.doctorappointmentapp.repository.ContactInformationRepository;
import com.javaguides.doctorappointmentapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements DoctorSubject {

    private List<DoctorObserver> observers = new ArrayList<>();

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    ContactInformationRepository contactInformationRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor addDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        notifyObservers(savedDoctor, "added");
        return savedDoctor;
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }


    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        if (updatedDoctor == null) {
            throw new IllegalArgumentException("Updated doctor data cannot be null");
        }

        System.out.println("Updating doctor with ID: " + id);

        // Check if the updated doctor has a contact information
        ContactInformation updatedContactInfo = updatedDoctor.getContactInformation();

        // Check if the updated doctor has a valid contact information
        if (updatedContactInfo != null) {
            // Check if the contact information needs to be saved or updated
            if (updatedContactInfo.getId() == null) {
                // Save the new contact information
                System.out.println("Saving new contact information for doctor with ID: " + id);
                ContactInformation savedContactInfo = contactInformationRepository.save(updatedContactInfo);
                updatedDoctor.setContactInformation(savedContactInfo);
            } else {
                // Update the existing contact information
                System.out.println("Updating contact information for doctor with ID: " + id);
                ContactInformation existingContactInfo = contactInformationRepository.findById(updatedContactInfo.getId()).orElseThrow();
                existingContactInfo.setEmail(updatedContactInfo.getEmail());
                existingContactInfo.setPhone(updatedContactInfo.getPhone());
                existingContactInfo.setAddress(updatedContactInfo.getAddress());
                contactInformationRepository.save(existingContactInfo);
            }
        }

        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor savedDoctor = optionalDoctor.map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialty(updatedDoctor.getSpecialty());
            doctor.setContactInformation(updatedDoctor.getContactInformation());
            return doctorRepository.save(doctor);
        }).orElseGet(() -> {
            updatedDoctor.setId(id);
            return doctorRepository.save(updatedDoctor);
        });

        System.out.println("Doctor with ID " + id + " updated successfully: " + savedDoctor);
        notifyObservers(savedDoctor, "updated");
        return savedDoctor;
    }





    public void deleteDoctor(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        doctorOptional.ifPresent(doctor -> {
            doctorRepository.deleteById(id);
            notifyObservers(doctor, "deleted");
        });
    }

    @Override
    public void registerObserver(DoctorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(DoctorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Doctor doctor, String action) {
        for (DoctorObserver observer : observers) {
            observer.update(doctor, action);
        }
    }
}
