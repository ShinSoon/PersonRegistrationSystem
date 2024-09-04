package com.LonpacInterviewAssignment.project.project_create_demo.service;

import com.LonpacInterviewAssignment.project.project_create_demo.model.Person;
import com.LonpacInterviewAssignment.project.project_create_demo.repository.PersonRepository;
import com.LonpacInterviewAssignment.project.project_create_demo.repository.PostcodeTownRepository;
import com.LonpacInterviewAssignment.project.project_create_demo.model.PostcodeTown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PostcodeTownRepository postcodeTownRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PostcodeTownRepository postcodeTownRepository) {
        this.personRepository = personRepository;
        this.postcodeTownRepository = postcodeTownRepository;
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> getPersonByIcNumber(String icNumber) {
        return personRepository.findByIcNumber(icNumber);
    }

    @Override
    public Optional<Person> getPersonByPostcode(String postcode) {
        return personRepository.findByPostcode(postcode);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public boolean existsByIcNumber(String icNumber) {
        return personRepository.existsByIcNumber(icNumber);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public String getTownByPostcode(String postcode) {
        return postcodeTownRepository.findById(postcode)
                .map(PostcodeTown::getTown)
                .orElse(null);
    }
}