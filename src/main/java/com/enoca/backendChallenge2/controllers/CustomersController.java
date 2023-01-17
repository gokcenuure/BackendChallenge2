package com.enoca.backendChallenge2.controllers;

import com.enoca.backendChallenge2.dtos.CreateCustomerDto;
import com.enoca.backendChallenge2.dtos.UpdateCustomerDto;
import com.enoca.backendChallenge2.models.Customer;
import com.enoca.backendChallenge2.models.Order;
import com.enoca.backendChallenge2.results.DataResult;
import com.enoca.backendChallenge2.results.Result;
import com.enoca.backendChallenge2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomersController {

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }
    private CustomerService customerService;

    @PostMapping("/add")
    public Result create(@RequestBody CreateCustomerDto customer) {
        return this.customerService.create(customer);
    }

    @PutMapping("/update/{id}")
    public Result update(@RequestBody UpdateCustomerDto customer, @PathVariable int id) {
        return this.customerService.update(customer, id);
    }

    @DeleteMapping("/delete")
    public Result delete(int customerId) {
        return this.customerService.delete(customerId);
    }

    @GetMapping()
    public DataResult<List<Customer>> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/getById/{customerId}")
    public Result getByCustomerId(@PathVariable int customerId) {
        return this.customerService.getByCustomerId(customerId);
    }


    @GetMapping("/withoutorders")
    public DataResult<List<Customer>> getCustomersWithoutOrders() {
        return this.customerService.getCustomersWithoutOrders();
    }


    @GetMapping("/{letter}")
    public DataResult<List<Customer>> getCustomersByLetter(@PathVariable String letter) {
        return customerService.getCustomersByLetter(letter);
    }



}
