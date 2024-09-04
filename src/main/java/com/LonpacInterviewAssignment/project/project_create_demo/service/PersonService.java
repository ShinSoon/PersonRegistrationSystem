package com.LonpacInterviewAssignment.project.project_create_demo.service;

import com.LonpacInterviewAssignment.project.project_create_demo.model.Person;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person savePerson(Person person);
    Optional<Person> getPersonByIcNumber(String icNumber);
    Optional<Person> getPersonByPostcode(String postcode);
    List<Person> getAllPersons();
    boolean existsByIcNumber(String icNumber);
    void deletePerson(Long id);
    Person updatePerson(Person person);

    // Add this method for postcode-town validation
    String getTownByPostcode(String postcode);
}