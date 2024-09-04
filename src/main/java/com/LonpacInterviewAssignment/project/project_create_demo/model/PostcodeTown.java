package com.LonpacInterviewAssignment.project.project_create_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "postcode_town")
public class PostcodeTown {

    @Id
    @NotBlank(message = "Postcode is required")
    private String postcode;

    @NotBlank(message = "Town is required")
    private String town;

    // Default constructor
    public PostcodeTown() {}

    // Constructor with parameters
    public PostcodeTown(String postcode, String town) {
        this.postcode = postcode;
        this.town = town;
    }

    // Getters and setters
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

    // toString method for debugging
    @Override
    public String toString() {
        return "PostcodeTown{" +
                "postcode='" + postcode + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}