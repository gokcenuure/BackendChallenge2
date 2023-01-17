package com.enoca.backendChallenge2.services;

import com.enoca.backendChallenge2.dtos.CreateCustomerDto;
import com.enoca.backendChallenge2.dtos.UpdateCustomerDto;
import com.enoca.backendChallenge2.models.Customer;
import com.enoca.backendChallenge2.models.Order;
import com.enoca.backendChallenge2.results.DataResult;
import com.enoca.backendChallenge2.results.Result;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    Result create(CreateCustomerDto customerDto);

    Result update(UpdateCustomerDto customerDto, int id);

    Result delete(int customerId);

    DataResult<List<Customer>> getAll();

    DataResult<Customer> getByCustomerId(int customerId);

    DataResult<List<Customer>> getCustomersWithoutOrders();

    DataResult<List<Customer>> getCustomersByLetter(String letter);
}
