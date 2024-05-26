/**
 * Controller class defines REST endpoints for managing doctors.
 * This class handles HTTP requests related to doctors such as getting all doctors,
 * adding a new doctor, updating an existing doctor, and deleting a doctor.
 */
package com.javaguides.doctorappointmentapp.controller;

import com.javaguides.doctorappointmentapp.model.ContactInformation;
import com.javaguides.doctorappointmentapp.model.Doctor;
import com.javaguides.doctorappointmentapp.model.DoctorRequest;
import com.javaguides.doctorappointmentapp.observer.DoctorLogger;
import com.javaguides.doctorappointmentapp.repository.ContactInformationRepository;
import com.javaguides.doctorappointmentapp.service.DoctorService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins="http://localhost:3000")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorLogger doctorLogger; // Example observer

    @Autowired
    private ContactInformationRepository contactInformationRepository; // Assuming you have a repository for ContactInformation

    /**
     * Initializes the DoctorController by registering the DoctorLogger as an observer
     * to the DoctorService.
     */
    @PostConstruct
    public void init() {
        doctorService.registerObserver(doctorLogger);
    }
    /**
     * Retrieves all doctors.
     * @return List of all doctors.
     */
    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    /**
     * Adds a new doctor.
     * @param doctorRequest The doctor information to be added.
     * @return The added doctor.
     */
    // Add a new doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody DoctorRequest doctorRequest) {
        // Log the received doctor request for debugging
        System.out.println("Received Doctor Request: " + doctorRequest);

        // Create a new ContactInformation entity
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(doctorRequest.getEmail());
        contactInformation.setPhone(doctorRequest.getPhone());
        contactInformation.setAddress(doctorRequest.getAddress());

        // Save the ContactInformation entity
        ContactInformation savedContactInformation = contactInformationRepository.save(contactInformation);

        // Log the saved ContactInformation for debugging
        System.out.println("Saved Contact Information: " + savedContactInformation);

        // Create a new Doctor entity
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setSpecialty(doctorRequest.getSpecialty());
        doctor.setWorkingHours(doctorRequest.getWorkingHours());
        doctor.setContactInformation(savedContactInformation);
        doctor.setUsername(doctorRequest.getUsername());
        doctor.setPassword(doctorRequest.getPassword());

        // Save the Doctor entity
        Doctor savedDoctor = doctorService.addDoctor(doctor);

        // Log the saved Doctor for debugging
        System.out.println("Saved Doctor: " + savedDoctor);

        return savedDoctor;
    }
    /**
     * Updates an existing doctor.
     * @param id The ID of the doctor to be updated.
     * @param updatedDoctor The updated doctor information.
     * @return The updated doctor.
     */
    // Update an existing doctor
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        System.out.println("Update Doctor method called with ID: " + id);
        return doctorService.updateDoctor(id, updatedDoctor);
    }

    /**
     * Deletes a doctor by its ID.
     * @param id The ID of the doctor to be deleted.
     * @return ResponseEntity indicating the success of the operation.
     */
    // Delete a doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
