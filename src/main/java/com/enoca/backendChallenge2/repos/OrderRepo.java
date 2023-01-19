package com.enoca.backendChallenge2.repos;

import com.enoca.backendChallenge2.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findByCreateDateAfter(Date date);

}
