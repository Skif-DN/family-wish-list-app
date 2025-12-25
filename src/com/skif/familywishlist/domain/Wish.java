package com.skif.familywishlist.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Wish {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime fulfilledAt;
    private boolean fulfilled;
    private Person owner;

    public Wish(String title, String description, Person owner) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
        this.fulfilledAt = null;
        this.fulfilled = false;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getFulfilledAt() {
        return fulfilledAt;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void markAsFulfilled() {
        this.fulfilled = true;
        this.fulfilledAt = LocalDateTime.now();
    }

    public void markAsUnfulfilled() {
        this.fulfilled = false;
        this.fulfilledAt = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wish)) return false;
        Wish wish = (Wish) o;
        return Objects.equals(id, wish.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
