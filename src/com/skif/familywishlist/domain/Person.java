package com.skif.familywishlist.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Person {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private User user;
    private final String pinCode;

    private List<Wish> wishes = new ArrayList<>();

    public Person(String firstName, String lastName, LocalDate birthDate, Gender gender, String pinCode) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        if (pinCode == null || pinCode.isBlank()) {
            throw new IllegalArgumentException("Pin code must not be empty");
        }
        this.pinCode = pinCode;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Wish> getWishes() {
        return new ArrayList<>(wishes);
    }

    public void addWish(Wish wish) {
        wishes.add(wish);
    }

    public void removeWish(Wish wish) {
        wishes.remove(wish);
    }

    public boolean checkPin(String pin) {
        return pinCode.equals(pin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
