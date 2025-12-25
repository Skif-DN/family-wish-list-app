package com.skif.familywishlist.service;

import com.skif.familywishlist.domain.Person;
import com.skif.familywishlist.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonService {
    private List<Person> persons;

     public PersonService() {
        this.persons = new ArrayList<>();
     }

    public void addPerson(Person person) {
        if (person != null && !persons.contains(person)) {
            persons.add(person);
        }
    }

    public void deletePerson(Person person, WishService wishService) {
        if (person == null || wishService == null) return;

        wishService.getAllWishes().stream()
                .filter(w -> w.getOwner().equals(person))
                .collect(Collectors.toList())
                .forEach(wishService::removeWish);

        person.getWishes().clear();

        persons.remove(person);
    }

    public  List<Person> getPersons() {
        return new ArrayList<>(persons);
    }

    public Person getPersonById(UUID id) {
        if (id == null) return null;

        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void assignUser(Person person, User user) {
        if (person == null || user == null) {
            throw new IllegalArgumentException("Person and User must not be null");
        }

        if (!persons.contains(person)) {
            throw new IllegalStateException("Person does not exist");
        }

        if (person.getUser() != null) {
            throw new IllegalStateException("Person already has a user");
        }

        person.setUser(user);
    }
}
