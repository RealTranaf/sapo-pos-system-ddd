package com.sapo.customer.interfaces;

import com.sapo.customer.application.CustomerService;
import com.sapo.customer.domain.model.Customer;
import com.sapo.customer.domain.model.Gender;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers(@RequestParam(required = false) String keyword,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(required = false) String startDate,
                                             @RequestParam(required = false) String endDate,
                                             @RequestParam(required = false) Double minAmount,
                                             @RequestParam(required = false) Double maxAmount,
                                             @RequestParam(defaultValue = "createdAt") String sortBy,
                                             @RequestParam(defaultValue = "desc") String sortDir,
                                             @RequestParam(required = false) Gender gender
    ) {
        return ResponseEntity.ok(customerService.getAllCustomers(keyword, page, size,
                startDate, endDate, minAmount, maxAmount,
                sortBy, sortDir, gender));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest request) {
        customerService.createCustomer(request.name, request.phoneNum, request.gender, request.note);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> createCustomer(@PathVariable Integer id, @RequestBody UpdateCustomerRequest request) {
        customerService.updateCustomer(id, request.name, request.phoneNum, request.gender, request.note);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
