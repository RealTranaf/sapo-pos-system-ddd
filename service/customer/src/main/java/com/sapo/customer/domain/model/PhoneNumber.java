package com.sapo.customer.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(String value) {
    private static final String REGEX = "^0[2|3|5|7|8|9][0-9]{8,9}$";

    public PhoneNumber {
        if (value == null || !value.matches(REGEX)) {
            throw new RuntimeException("Invalid phone number");
        }
    }
}
