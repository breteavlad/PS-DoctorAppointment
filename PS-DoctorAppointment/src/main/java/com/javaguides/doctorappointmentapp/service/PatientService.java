package com.javaguides.doctorappointmentapp.service;

import com.javaguides.doctorappointmentapp.model.ContactInformationClient;
import com.javaguides.doctorappointmentapp.model.Patient;
import com.javaguides.doctorappointmentapp.observer.PatientObserver;
import com.javaguides.doctorappointmentapp.observer.PatientSubject;
import com.javaguides.doctorappointmentapp.repository.ContactInformationClientRepository;
import com.javaguides.doctorappointmentapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements PatientSubject {

    private List<PatientObserver> observers = new ArrayList<>();

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    ContactInformationClientRepository contactInformationClientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient addPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        notifyObservers(savedPatient, "added");
        return savedPatient;
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        System.out.println("Updating patient with ID: " + updatedPatient);

        // Retrieve the patient by ID
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        System.out.println("Optional patient: " + optionalPatient);
        Patient existingPatient = optionalPatient.orElse(null);

        // Print the patient found by ID
        System.out.println("Patient found by ID " + id + ": " + existingPatient);

        if (existingPatient != null) {
            // Update the patient data
            existingPatient.setPatientName(updatedPatient.getPatientName());
            existingPatient.setDiseaseDescription(updatedPatient.getDiseaseDescription());

            // Get the updated contact information
            ContactInformationClient updatedContactInfo = updatedPatient.getContactInformationClient();

            // Ensure existingPatient has non-null contact information
            if (existingPatient.getContactInformationClient() == null) {
                existingPatient.setContactInformationClient(new ContactInformationClient());
            }

            if (updatedContactInfo != null) {
                // Check if the contact information needs to be saved or updated
                if (updatedContactInfo.getId() == null) {
                    try {
                        // Save the new contact information
                        System.out.println("Saving new contact information for patient with ID: " + id);
                        System.out.println("Updated info: " + updatedContactInfo);
                        ContactInformationClient savedContactInfo = contactInformationClientRepository.save(updatedContactInfo);
                        System.out.println("Saved info: " + savedContactInfo);
                        existingPatient.setContactInformationClient(savedContactInfo);
                    }catch(DataAccessException e){
                        System.err.println("Error occurred while saving contact information: " + e.getMessage());

                    }
                } else {
                    // Update the existing contact information
                    System.out.println("Updating contact information for patient with ID: " + id);
                    existingPatient.getContactInformationClient().setEmail(updatedContactInfo.getEmail());
                    existingPatient.getContactInformationClient().setPhone(updatedContactInfo.getPhone());
                    existingPatient.getContactInformationClient().setAddress(updatedContactInfo.getAddress());
                }
            }

            // Save the updated patient
            Patient savedPatient = patientRepository.save(existingPatient);
            System.out.println("Patient with ID " + id + " updated successfully");
            notifyObservers(savedPatient, "updated");
            return savedPatient;
        } else {
            // Handle the case where the patient with the given ID is not found
            return null;
        }
    }




    public void deletePatient(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        patientOptional.ifPresent(patient -> {
            patientRepository.deleteById(id);
            notifyObservers(patient, "deleted");
        });
    }

    @Override
    public void registerObserver(PatientObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PatientObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Patient patient, String action) {
        for (PatientObserver observer : observers) {
            observer.update(patient, action);
        }
    }
}
