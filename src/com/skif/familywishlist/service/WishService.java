package com.skif.familywishlist.service;

import com.skif.familywishlist.domain.Person;
import com.skif.familywishlist.domain.Wish;

import java.util.ArrayList;
import java.util.List;

public class WishService {
    private List<Wish> wishList;

    public WishService() {
        wishList = new ArrayList<>();
    }

    public void addWish(Person person, String pin, Wish wish) {
        if (person == null || wish == null) {
            throw new IllegalArgumentException("Person and wish must not be null");
        }

        if (!person.checkPin(pin)) {
            throw new SecurityException("Invalid pin code");
        }

        if (!wish.getOwner().equals(person)) {
            throw new IllegalStateException("Wish owner mismatch");
        }

        person.addWish(wish);
        wishList.add(wish);
    }

    public void removeWish(Wish wish) {
        if (wish == null) return;

        wishList.remove(wish);

        if (wish.getOwner() != null) {
            wish.getOwner().removeWish(wish);
        }
    }

    public void markWishAsFulfilled(Wish wish, String pin) {
        Person owner = wish.getOwner();

        if (!owner.checkPin(pin)) {
            throw new SecurityException("Invalid pin code");
        }

        if (wish.isFulfilled()) {
            throw new IllegalStateException("Wish already fulfilled");
        }
        wish.markAsFulfilled();
    }

    public void markWishAsUnfulfilled(Wish wish, String pin) {
        Person owner = wish.getOwner();

        if (!owner.checkPin(pin)) {
            throw new SecurityException("Invalid pin code");
        }

        if (!wish.isFulfilled()) {
            throw new IllegalStateException("Wish already unfulfilled");
        }
        wish.markAsUnfulfilled();
    }

    public List<Wish> getAllWishes() {
        return new ArrayList<>(wishList);
    }
}
