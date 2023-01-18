package com.enoca.backendChallenge2.services;

import com.enoca.backendChallenge2.dtos.CreateCustomerDto;
import com.enoca.backendChallenge2.dtos.UpdateCustomerDto;
import com.enoca.backendChallenge2.exceptions.CustomerNotFoundException;
import com.enoca.backendChallenge2.models.Customer;
import com.enoca.backendChallenge2.repos.CustomerRepo;
import com.enoca.backendChallenge2.repos.OrderRepo;
import com.enoca.backendChallenge2.results.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerManager implements CustomerService{


    @Autowired
    public CustomerManager(ModelMapper modelMapper, CustomerRepo customerRepo, OrderRepo orderRepo) {
        this.modelMapper = modelMapper;
        this.customerRepo = customerRepo;
    }


    private ModelMapper modelMapper;
    private CustomerRepo customerRepo;

    @Override
    public Result create(CreateCustomerDto customerDto) {
        Customer customerToCreate = modelMapper.map(customerDto, Customer.class);
        this.customerRepo.save(customerToCreate);
        return new SuccessResult("Customer added");
    }

    @Override
    public Result update(UpdateCustomerDto customerDto, int id) {
        Customer UpdateCustomerDto = modelMapper.map(customerDto, Customer.class);
        Customer customerToUpdate = this.getByCustomerId(id).getData();

        customerToUpdate.setName(customerDto.getName());
        customerToUpdate.setAge(customerDto.getAge());

        customerRepo.save(customerToUpdate);
        return new SuccessResult("Customer updated");
    }

    @Override
    public Result delete(int customerId) {
        Customer customer = this.getByCustomerId(customerId).getData();
        return new SuccessResult("Customer deleted");
    }

    @Override
    public DataResult<List<Customer>> getAll() {
        return new SuccessDataResult<List<Customer>>
                (this.customerRepo.findAll(),"Customers listed");
    }

    @Override
    public DataResult<Customer> getByCustomerId(int customerId) {
        try {
            Customer customer = this.customerRepo.findById(customerId).orElse(null);
            if (customer==null) {
                throw new CustomerNotFoundException("Invalid customer id");
            }
            return new SuccessDataResult<Customer>(customer,"Customer found");
        } catch (CustomerNotFoundException ex) {
            return new ErrorDataResult("Error: " + ex.getMessage());
        }
    }


    @Override
    public DataResult<List<Customer>> getCustomersWithoutOrders() {
        List<Customer> customersWithoutOrders = customerRepo.findAllCustomersWithoutOrders();

        return new SuccessDataResult<List<Customer>>(customersWithoutOrders, "");
    }


    @Override
    public DataResult<List<Customer>> getCustomersByLetter(String letter) {
        List<Customer> customers = customerRepo.findByNameContaining(letter);
        List<Customer> customersWithOrders = new ArrayList<>();
        for (Customer customer : customers) {
            if (!customer.getOrders().isEmpty()) {
                customersWithOrders.add(customer);
            }}
        return new DataResult<>(true, customersWithOrders);   }}

