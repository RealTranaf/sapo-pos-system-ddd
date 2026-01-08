package com.sapo.customer.interfaces;

import com.sapo.customer.domain.model.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerQueryParams {
    private String keyword;

    private int page = 0;
    private int size = 10;

    private String startDate;
    private String endDate;

    private Double minAmount;
    private Double maxAmount;

    private String sortBy = "createdAt";
    private String sortDir = "desc";

    private Gender gender;
}
