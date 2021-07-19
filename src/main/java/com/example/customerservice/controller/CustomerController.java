package com.example.customerservice.controller;

import com.example.customerservice.dao.CustomerDao;
import com.example.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerDao customerDao;
    private Environment environment;


    @Autowired
    public CustomerController(CustomerDao customerDao,Environment environment) {
        this.customerDao = customerDao;
        this.environment=environment;
    }

    @GetMapping
    public ResponseEntity<String> getStatus()
    {
        return ResponseEntity.ok("up and running on port: "+environment.getProperty("local.server.port"));
    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer()
    {
        return ResponseEntity.ok(customerDao.findAll());
    }
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDao.save(customer));
    }
}