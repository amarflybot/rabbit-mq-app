package com.example.rabbitmqapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomerCommand implements ApplicationRunner {

    private final CustomerRepo customerRepo;

    public CustomerCommand(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Arrays.asList("Amar","Prateek","Deepesh")
                .stream()
                .forEach( name-> {
                    customerRepo.save(new Customer(name));
                });
    }
}
