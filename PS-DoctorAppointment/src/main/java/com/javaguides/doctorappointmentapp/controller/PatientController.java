package com.javaguides.doctorappointmentapp.controller;

import com.javaguides.doctorappointmentapp.model.ContactInformationClient;
import com.javaguides.doctorappointmentapp.model.Patient;
import com.javaguides.doctorappointmentapp.model.PatientRequest;
import com.javaguides.doctorappointmentapp.observer.PatientLogger;
import com.javaguides.doctorappointmentapp.repository.ContactInformationClientRepository;
import com.javaguides.doctorappointmentapp.service.PatientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins="http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientLogger patientLogger; // Example observer

    @Autowired
    private ContactInformationClientRepository contactInformationClientRepository; // Assuming you have a repository for ContactInformationClient

    /**
     * Initializes the PatientController by registering the PatientLogger as an observer
     * to the PatientService.
     */
    @PostConstruct
    public void init() {
        patientService.registerObserver(patientLogger);
    }

    /**
     * Retrieves all patients.
     *
     * @return List of all patients.
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Adds a new patient.
     *
     * @param patientRequest The patient information to be added.
     * @return The added patient.
     */
    @PostMapping
    public Patient addPatient(@RequestBody PatientRequest patientRequest) {
        //I have used patient Request in order to combine the two classes into one to be able to use them in the body
        System.out.println("Received Patient Request: " + patientRequest);

        // Create a new ContactInformationClient entity
        ContactInformationClient contactInformationClient = new ContactInformationClient();
        contactInformationClient.setEmail(patientRequest.getEmail());
        contactInformationClient.setPhone(patientRequest.getPhone());
        contactInformationClient.setAddress(patientRequest.getAddress());

        // Save the ContactInformationClient entity
        ContactInformationClient savedContactInformationClient = contactInformationClientRepository.save(contactInformationClient);


        System.out.println("Saved Contact Information Client: " + savedContactInformationClient);

        // Create a new Patient entity
        Patient patient = new Patient();
        patient.setPatientName(patientRequest.getPatientName());
        patient.setDiseaseDescription(patientRequest.getDiseaseDescription());
        patient.setPassword(patientRequest.getPassword());
        patient.setUsername(patientRequest.getUsername());
        patient.setContactInformationClient(savedContactInformationClient);

        // Save the Patient entity
        Patient savedPatient = patientService.addPatient(patient);

        // Log the saved Patient for debugging
        System.out.println("Saved Patient: " + savedPatient);

        return savedPatient;
    }

    /**
     * Updates an existing patient.
     *
     * @param id              The ID of the patient to be updated.
     * @param updatedPatient The updated patient information.
     * @return The updated patient.
     */
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        System.out.println("Update Patient method called with ID: " + id);
        return patientService.updatePatient(id, updatedPatient);
    }

    /**
     * Deletes a patient by its ID.
     *
     * @param id The ID of the patient to be deleted.
     * @return ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
