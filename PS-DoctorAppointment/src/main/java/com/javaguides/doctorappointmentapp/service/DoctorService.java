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
        System.out.println("Updating doctor with ID: " + id);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        System.out.println("Doctor found by ID " + id + ": " + optionalDoctor.orElse(null)); // Print the doctor found by ID
        return doctorRepository.findById(id)
                .map(existingDoctor -> {
                    existingDoctor.setName(updatedDoctor.getName());
                    existingDoctor.setSpecialty(updatedDoctor.getSpecialty());

                    // Get the updated contact information
                    ContactInformation updatedContactInfo = updatedDoctor.getContactInformation();

                    if (updatedContactInfo != null) {
                        // Check if the contact information needs to be saved or updated
                        if (updatedContactInfo.getId() == null) {
                            // Save the new contact information
                            System.out.println("Saving new contact information for doctor with ID: " + id);
                            ContactInformation savedContactInfo = contactInformationRepository.save(updatedContactInfo);
                            existingDoctor.setContactInformation(savedContactInfo);
                        } else {
                            // Update the existing contact information
                            System.out.println("Updating contact information for doctor with ID: " + id);
                            existingDoctor.getContactInformation().setEmail(updatedContactInfo.getEmail());
                            existingDoctor.getContactInformation().setPhone(updatedContactInfo.getPhone());
                            existingDoctor.getContactInformation().setAddress(updatedContactInfo.getAddress());
                        }
                    }

                    // Update the working hours
                    existingDoctor.setWorkingHours(updatedDoctor.getWorkingHours());

                    // Save the updated doctor
                    Doctor savedDoctor = doctorRepository.save(existingDoctor);
                    System.out.println("Doctor with ID " + id + " updated successfully");
                    notifyObservers(savedDoctor, "updated");
                    return savedDoctor;
                })
                .orElse(null); // or throw an exception if needed
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
