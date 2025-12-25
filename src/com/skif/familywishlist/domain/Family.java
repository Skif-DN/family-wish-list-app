package com.skif.familywishlist.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Family {
    private UUID id;
    private String name;
    private List<Person> members;

    public Family(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.members = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void addMember(Person person) {
        members.add(person);
    }

    public void removeMember(Person person) {
        members.remove(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Family)) return false;
        Family family = (Family) o;
        return Objects.equals(id, family.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
