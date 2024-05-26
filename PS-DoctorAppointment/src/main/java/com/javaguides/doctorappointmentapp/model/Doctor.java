/**
 * Doctor class represents a doctor entity with associated information such as name, specialty,
 * contact information, and working hours.
 */
package com.javaguides.doctorappointmentapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String specialty;
    @Column(nullable = false, length = 45)
    private String username;


    @Column(nullable = false, length = 45)
    private String password;
    @OneToOne
    @JoinColumn(name = "contact_information_id", referencedColumnName = "id")
    private ContactInformation contactInformation;

    @Column(nullable = false, length = 45)
    private String workingHours;

    // Constructors

    public Doctor() {
    }

    public Doctor(Long id, String name, String specialty, ContactInformation contactInformation, String workingHours) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.contactInformation = contactInformation;
        this.workingHours = workingHours;
    }

    // Getters and Setters

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    // toString method for debugging/logging purposes
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", contactInformation=" + contactInformation +
                ", workingHours='" + workingHours + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
