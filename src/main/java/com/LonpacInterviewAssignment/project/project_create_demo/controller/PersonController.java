package com.LonpacInterviewAssignment.project.project_create_demo.controller;

import com.LonpacInterviewAssignment.project.project_create_demo.model.Person;
import com.LonpacInterviewAssignment.project.project_create_demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // Web form handling methods
    @GetMapping("/new")
    public String showPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/save")
    public String submitPersonForm(@Valid @ModelAttribute Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "person-form";
        }

        // Custom validation for IC Number format
        if (!isValidIcNumber(person.getIcNumber())) {
            bindingResult.rejectValue("icNumber", "error.icNumber", "Invalid IC Number format");
            return "person-form";
        }

        // Custom validation for Postcode-Town relationship
        if (!isValidPostcodeTown(person.getPostcode(), person.getTown())) {
            bindingResult.rejectValue("postcode", "error.postcode", "Invalid Postcode-Town combination");
            return "person-form";
        }

        try {
            personService.savePerson(person);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving person: " + e.getMessage());
            return "person-form";
        }

        return "redirect:/persons/new?success";
    }

    private boolean isValidIcNumber(String icNumber) {
        // Implement IC Number validation logic
        // This is a placeholder implementation. Adjust according to your IC Number format.
        return icNumber != null && icNumber.matches("\\d{12}");
    }

    private boolean isValidPostcodeTown(String postcode, String town) {
        // Implement Postcode-Town validation logic

        Map<String, String> postcodeTownMap = Map.of(
                "08000", "Sungai Petani",
                "43200", "Cheras"
        );
        return postcodeTownMap.getOrDefault(postcode, "").equals(town);
    }


    // RESTful API methods
    @PostMapping
    @ResponseBody
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.ok(savedPerson);
    }
    @GetMapping("/{icNumber}")
    @ResponseBody
    public ResponseEntity<Person> getPersonByIcNumber(@PathVariable String icNumber) {
        return personService.getPersonByIcNumber(icNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        person.setId(id);
        Person updatedPerson = personService.updatePerson(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/postcode/{postcode}")
    @ResponseBody
    public ResponseEntity<Person> getPersonByPostcode(@PathVariable String postcode) {
        return personService.getPersonByPostcode(postcode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}