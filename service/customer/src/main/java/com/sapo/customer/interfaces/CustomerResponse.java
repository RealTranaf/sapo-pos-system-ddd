package com.sapo.customer.interfaces;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.sapo.customer.domain.model.Customer;
import com.sapo.customer.domain.model.Gender;
import lombok.*;

import java.time.Instant;

@Getter
@NoArgsConstructor
@JsonRootName("customer")
public class CustomerResponse {

    private Integer id;
    private String name;
    private String phoneNum;
    private Gender gender;
    private String note;
    private Instant createdOn;
    private Instant lastPurchaseDate;
    private double totalPurchaseAmount;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phoneNum = customer.getPhoneNum();
        this.gender = customer.getGender();
        this.note = customer.getNote();
        this.createdOn = customer.getCreatedOn();
        this.lastPurchaseDate = customer.getLastPurchaseDate();
        this.totalPurchaseAmount = customer.getTotalPurchaseAmount();
    }
}