package com.skif.familywishlist.service;

import com.skif.familywishlist.domain.Family;
import com.skif.familywishlist.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class FamilyService {
    private List<Family> families;

    public FamilyService() {
        this.families = new ArrayList<>();
    }

    public List<Family> getFamilies() {
        return families;
    }

    public void createFamily(Family family) {
        if (family == null) {
            throw new IllegalArgumentException("Family must not be null");
        }

        if (!families.contains(family)) {
            families.add(family);
        }
    }

    public void addMemberToFamily(Family family, Person person) {
        if (family == null || person == null) {
            throw new IllegalArgumentException("Family and person must not be null");
        }

        if (!family.getMembers().contains(person)) {
            family.addMember(person);
        }
    }

    public void removeMemberFromFamily(Family family, Person person) {
        if (family != null && person != null) {
            family.removeMember(person);
        }
    }

    public List<Person> getMembers(Family family){
        if (family != null) {
            return new ArrayList<>(family.getMembers());
        }
        return new ArrayList<>();
    }
}
