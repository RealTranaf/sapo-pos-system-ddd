package com.sapo.customer.infrastructure;

import com.sapo.customer.domain.model.Customer;
import com.sapo.customer.domain.model.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JpaCustomerRepository extends JpaRepository<Customer, Integer>, CustomerRepository, JpaSpecificationExecutor<Customer> {
}
