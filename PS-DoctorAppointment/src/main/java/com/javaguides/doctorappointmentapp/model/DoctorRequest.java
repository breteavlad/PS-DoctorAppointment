/**
 * DoctorRequest class represents a request object for creating a new doctor.
 * It contains attributes such as name, specialty, contact information, and working hours.
 */
package com.javaguides.doctorappointmentapp.model;

public class DoctorRequest {
    private String name;
    private String specialty;
    private String email;
    private String phone;
    private String address;
    private String workingHours;



    private String username;



    private String password;

    // Constructors

    public DoctorRequest() {
    }

    public DoctorRequest(String name, String specialty, String email, String phone, String address, String workingHours,String username,String password) {
        this.name = name;
        this.specialty = specialty;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.workingHours = workingHours;
        this.username=username;
        this.password=password;
    }

    // Getters and Setters


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}

