package com.sapo.customer.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, unique = true, length = 12)
    private String phoneNum;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "created_at", updatable = false)
    private Instant createdOn;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String note;

    private Instant lastPurchaseDate;

    private double totalPurchaseAmount;

    public static Customer create(String name, PhoneNumber phoneNumber, Gender gender, String note) {
        return new Customer(name, phoneNumber, gender, note);
    }

    private Customer(String name, PhoneNumber phoneNumber, Gender gender, String note) {
        validateName(name);
        this.name = name.trim();
        this.phoneNum = phoneNumber.value();
        this.gender = gender == null ? Gender.NaN : gender;
        this.note = note;
    }

    public void update(String name, PhoneNumber phoneNumber, Gender gender, String note) {
        validateName(name);
        this.name = name.trim();
        this.phoneNum = phoneNumber.value();
        this.gender = gender;
        this.note = note;
    }

    public void addPurchase(double amount, Instant purchasedAt) {
        if (amount <= 0) {
            throw new RuntimeException("Purchase amount must be positive");
        }
        this.totalPurchaseAmount += amount;
        this.lastPurchaseDate = purchasedAt;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Name is required");
        }
        if (name.length() < 2 || name.length() > 40) {
            throw new RuntimeException("Name must be 2–40 characters");
        }
    }
}
