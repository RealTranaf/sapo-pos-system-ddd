package com.sapo.customer.domain.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Integer id);
    Optional<Customer> findByPhoneNum(String phoneNum);
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);
    Customer save(Customer customer);
}
