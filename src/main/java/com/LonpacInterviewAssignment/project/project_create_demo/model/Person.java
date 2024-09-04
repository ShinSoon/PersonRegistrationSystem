package com.LonpacInterviewAssignment.project.project_create_demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "IC Number is required")
    @Column(name = "ic_number", unique = true, nullable = false)
    private String icNumber;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^[MF]$", message = "Gender must be 'M' or 'F'")
    @Column(nullable = false)
    private String gender;

    @NotNull(message = "Date of Birth is required")
    @Past(message = "Date of Birth must be in the past")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Postcode is required")
    @Column(nullable = false)
    private String postcode;

    @NotBlank(message = "Town is required")
    @Column(nullable = false)
    private String town;

    // Constructors
    public Person() {}

    public Person(String icNumber, String gender, LocalDate dateOfBirth, String postcode, String town) {
        this.icNumber = icNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.postcode = postcode;
        this.town = town;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", icNumber='" + icNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", postcode='" + postcode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}