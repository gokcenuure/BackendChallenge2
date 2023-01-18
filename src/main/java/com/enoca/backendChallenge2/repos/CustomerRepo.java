package com.enoca.backendChallenge2.repos;

import com.enoca.backendChallenge2.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {


    List<Customer> findByNameContaining(String letter);

    @Query(value = "SELECT * FROM customers\n" +
            "WHERE customer_id NOT IN (SELECT customer_id FROM orders)", nativeQuery = true)
    List<Customer> findAllCustomersWithoutOrders();
}
