package com.LonpacInterviewAssignment.project.project_create_demo.repository;

import com.LonpacInterviewAssignment.project.project_create_demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByIcNumber(String icNumber);

    Optional<Person> findByPostcode(String postcode);

    boolean existsByIcNumber(String icNumber);

    List<Person> findByTown(String town);
}