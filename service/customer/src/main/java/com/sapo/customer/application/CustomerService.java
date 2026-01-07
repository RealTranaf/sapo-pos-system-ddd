package com.sapo.customer.application;

import com.sapo.customer.domain.model.Customer;
import com.sapo.customer.domain.model.CustomerRepository;
import com.sapo.customer.domain.model.Gender;
import com.sapo.customer.domain.model.PhoneNumber;
import com.sapo.customer.infrastructure.CustomerSpecification;
import com.sapo.customer.util.DateTimeUtils;
import com.sapo.customer.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Tạo customer từ name, phoneNum, gender và note
    public void createCustomer(String name, String phoneNum, Gender gender, String note) {
        PhoneNumber phoneNumber = new PhoneNumber(phoneNum);

        customerRepository.findByPhoneNum(phoneNum)
                .ifPresent(c -> {
                    throw new RuntimeException("Phone number already exists");
                });

        Customer customer = Customer.create(name, phoneNumber, gender, note);
        customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer does not exist"));
    }

    public Page<Customer> getAllCustomers(String keyword, int page, int size, String startDate, String endDate,
                                         Double minAmount, Double maxAmount,
                                         String sortBy, String sortDir, Gender gender) {
        String sortField = StringUtils.hasText(sortBy) ? sortBy : "createdAt";

        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Instant startInstant = null;
        Instant endInstant = null;

        if (StringUtils.hasText(startDate) && StringUtils.hasText(endDate)) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            startInstant = DateTimeUtils.startOfDayUtc(start);
            endInstant = DateTimeUtils.endOfDayUtc(end);
        }

        Specification<Customer> spec = Specification
                .where(CustomerSpecification.containKeyword(keyword))
                .and(CustomerSpecification.purchaseDateBetween(startInstant, endInstant))
                .and(CustomerSpecification.genderEquals(gender == null ? null : gender))
                .and(CustomerSpecification.purchaseAmountBetween(minAmount, maxAmount));

        return customerRepository.findAll(spec, pageable);
    }

    public void updateCustomer(Integer id, String name, String phoneNum, Gender gender, String note) {
        Customer customer = getCustomerById(id);
        customer.update(name, new PhoneNumber(phoneNum), gender, note);
        customerRepository.save(customer);
    }
}
