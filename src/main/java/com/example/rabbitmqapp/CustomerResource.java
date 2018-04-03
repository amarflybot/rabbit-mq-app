package com.example.rabbitmqapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/replay")
@RestController
public class CustomerResource {

    private final CustomerRepo customerRepo;

    private final CustomerService customerService;

    public CustomerResource(CustomerRepo customerRepo, CustomerService customerService) {
        this.customerRepo = customerRepo;
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> replayById(@PathVariable("id") Long id) {
        customerRepo.findById(id).ifPresent( customer -> {
            customerService.createCustomer(customer);
            ResponseEntity.ok(customer);
        });
        return ResponseEntity.ok().build();
    }
}
