package com.bloxbean.cardano.metdataindexer;

import com.bloxbean.cardano.metdataindexer.db.Customer;
import com.bloxbean.cardano.metdataindexer.db.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CustomerProcessor {
    private final CustomerRepository repository;
    private int counter = 2;

    @Transactional
    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setId((long)counter++);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        if (counter == 100) {
            customer.setLastName(null);
            counter = 2;
        }
        repository.save(customer);
        System.out.println("Counter: " + counter);
    }
}
