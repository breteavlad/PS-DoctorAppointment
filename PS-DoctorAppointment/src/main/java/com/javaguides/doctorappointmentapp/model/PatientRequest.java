package com.javaguides.doctorappointmentapp.model;

public class PatientRequest {
    private String patientName;
    private String email;
    private String phone;
    private String address;
    private String diseaseDescription;

    // Constructors

    public PatientRequest() {
    }

    public PatientRequest(String name, String email, String phone, String address, String diseaseDescription) {
        this.patientName = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.diseaseDescription = diseaseDescription;
    }

    // Getters and Setters

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String name) {
        this.patientName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }
}
