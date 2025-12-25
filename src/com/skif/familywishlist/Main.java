package com.skif.familywishlist;

import com.skif.familywishlist.domain.*;
import main.java.com.skif.familywishlist.domain.*;
import com.skif.familywishlist.service.FamilyService;
import com.skif.familywishlist.service.PersonService;
import com.skif.familywishlist.service.UserService;
import com.skif.familywishlist.service.WishService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
//  Create services objects
        UserService userService = new UserService();
        PersonService personService = new PersonService();
        FamilyService familyService = new FamilyService();
        WishService wishService = new WishService();

// Create new User
        User adminUser = userService.createUser("admin", "admin");

        Person adminPerson = new Person("John", "Smith", LocalDate.of(1990, 1, 10), Gender.MALE, "1234");
        personService.addPerson(adminPerson);
        personService.assignUser(adminPerson, adminUser);

        Family family = new Family("Smith");
        familyService.createFamily(family);
        familyService.addMemberToFamily(family, adminPerson);

        User annaUser = userService.createUser("anna", "anna");

        Person anna = new Person("Anna", "Smith", LocalDate.of(1990, 1, 10), Gender.FEMALE, "1234");
        personService.addPerson(anna);
        personService.assignUser(anna, annaUser);
        familyService.addMemberToFamily(family, anna);

        System.out.println("Welcome to family wishlist!");
        System.out.println("Family: " + family.getName());
        for (Person person : family.getMembers()) {
            System.out.println("- " + person.getFirstName() + " " + person.getLastName() + " " + person.getUser().getUsername());
        }

//  Delete Persons
        familyService.removeMemberFromFamily(family, anna);
        personService.deletePerson(anna,wishService);
        System.out.println("Family: " + family.getName());
        for (Person person : family.getMembers()) {
            System.out.println("- " + person.getFirstName() + " " + person.getLastName() + " " + person.getUser().getUsername());
        }

//  Test getPersons
        System.out.println("\n=== TEST getPersons ===");

        List<Person> persons = personService.getPersons();
        System.out.println("Persons count: " + persons.size());

        persons.clear();

        System.out.println("Persons count after external clear: "
                + personService.getPersons().size());

//  Test personById
        System.out.println("\n=== TEST getPersonById ===");

        UUID adminId = adminPerson.getId();

        Person found = personService.getPersonById(adminId);
        System.out.println("Found person: " + found.getFirstName());

        Person notFound = personService.getPersonById(UUID.randomUUID());
        System.out.println("Not found: " + notFound);

        Person nullId = personService.getPersonById(null);
        System.out.println("Null id result: " + nullId);

//   Test Wish
        System.out.println("\n=== TEST wish list ===");

        Wish newWish = new Wish("Laptop", "I wish to buy laptop", adminPerson);

        wishService.addWish(adminPerson, "1234", newWish);

        adminPerson.getWishes().forEach(w ->
                System.out.println(w.getTitle() + " | Description: " + w.getDescription() + " | fulfilled: " + w.isFulfilled()));

        wishService.markWishAsFulfilled(newWish, "1234");
        adminPerson.getWishes().forEach(w ->
                System.out.println(w.getTitle() + " | Description: " + w.getDescription() + " | fulfilled: " + w.isFulfilled()));

        wishService.markWishAsUnfulfilled(newWish,  "1234");
        adminPerson.getWishes().forEach(w ->
                System.out.println(w.getTitle() + " | Description: " + w.getDescription() + " | fulfilled: " + w.isFulfilled()));

        wishService.removeWish(newWish);
                System.out.println("Wish count: " + adminPerson.getWishes().size());




        User anna2User = userService.createUser("anna2", "anna2");
        Person anna2 = new Person("Anna", "Smith", LocalDate.of(1995, 5, 5), Gender.FEMALE,  "1234");
        personService.addPerson(anna2);
        personService.assignUser(anna2, anna2User);
        familyService.addMemberToFamily(family, anna2);
        Wish annaWish = new Wish(
                "Phone",
                "iPhone",
                anna2
        );

        wishService.addWish(anna2,"1234", annaWish);

        wishService.markWishAsFulfilled(annaWish,  "1234");
        System.out.println("Wish '" + annaWish.getTitle() + "' fulfilled: " + annaWish.isFulfilled());


        try {
            wishService.markWishAsFulfilled(annaWish, "4321");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }

}
