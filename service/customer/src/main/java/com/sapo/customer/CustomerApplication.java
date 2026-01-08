package com.sapo.customer;

import com.sapo.customer.application.CustomerService;
import com.sapo.customer.domain.model.CustomerRepository;
import com.sapo.customer.domain.model.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CustomerApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() == 0) {
            customerService.createCustomer("Nguyen Van A", "0901111111", Gender.MALE, "VIP");
            customerService.createCustomer("Tran Thi B", "0902222222", Gender.FEMALE,"Frequent buyer");
            customerService.createCustomer("Le Van C", "0903333333", Gender.MALE,null);
            customerService.createCustomer("Pham Thi D", "0904444444", Gender.FEMALE,"Returning after 6 months");
            customerService.createCustomer("Hoang Van E", "0905555555", Gender.NaN,"New customer");
            customerService.createCustomer("Do Minh F", "0906666666", Gender.FEMALE,"Occasional buyer");
            customerService.createCustomer("Ly Thi G", "0907777777", Gender.FEMALE,null);
            customerService.createCustomer("Vo Van H", "0908888888", Gender.MALE,"VIP - high spender");
            customerService.createCustomer("Bui Thi I", "0909999999", Gender.FEMALE,"Prefers cash payments");
            customerService.createCustomer("Dang Van K", "0910000000", Gender.NaN,null);
            customerService.createCustomer("Nguyen Van M", "0911111111", Gender.MALE, null);
            customerService.createCustomer("Tran Thi N", "0912222222", Gender.FEMALE, "Prefers Visa payment");
            customerService.createCustomer("Hoang Gia P", "0913333333", Gender.MALE, "New buyer");
            customerService.createCustomer("Luong Thi Q", "0914444444", Gender.FEMALE, "Member card holder");
            customerService.createCustomer("Dang Minh R", "0915555555", Gender.MALE, null);
            customerService.createCustomer("Do Thi S", "0916666666", Gender.FEMALE, "Frequent returns");
            customerService.createCustomer("Vo Thanh T", "0917777777", Gender.MALE, "VIP 2");
            customerService.createCustomer("Ngo Thi U", "0918888888", Gender.FEMALE, null);
            customerService.createCustomer("Bui Minh V", "0919999999", Gender.MALE, "Cash buyer");
            customerService.createCustomer("Le Hoang X", "0920000000", Gender.MALE, null);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
