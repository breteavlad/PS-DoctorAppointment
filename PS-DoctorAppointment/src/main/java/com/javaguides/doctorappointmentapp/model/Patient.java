package com.javaguides.doctorappointmentapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String patientName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id", referencedColumnName = "id")
    private ContactInformationClient contactInformation;

    @Column(nullable = false, length = 255)
    private String diseaseDescription;



    @Column(nullable= false, length=45)
    private String username;


    @Column(nullable = false,length=45)
    private String password;

    // Constructors

    public Patient() {
    }

    public Patient(String patientName, ContactInformationClient contactInformation, String diseaseDescription,String password) {
        this.patientName = patientName;
        this.contactInformation = contactInformation;
        this.diseaseDescription = diseaseDescription;
        this.password=password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public ContactInformationClient getContactInformationClient() {
        return contactInformation;
    }

    public void setContactInformationClient(ContactInformationClient contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", contactInformation=" + contactInformation +
                ", diseaseDescription='" + diseaseDescription + '\'' +
                '}';
    }
}
