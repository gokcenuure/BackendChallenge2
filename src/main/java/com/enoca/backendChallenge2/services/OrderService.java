package com.enoca.backendChallenge2.services;

import com.enoca.backendChallenge2.dtos.CreateOrderDto;
import com.enoca.backendChallenge2.dtos.UpdateOrderDto;
import com.enoca.backendChallenge2.models.Order;
import com.enoca.backendChallenge2.results.DataResult;
import com.enoca.backendChallenge2.results.Result;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Result create(CreateOrderDto orderDto);

    Result update(UpdateOrderDto orderDto, int id);

    Result delete(int orderId);

    DataResult<List<Order>> getAll();

    DataResult<Order> getByOrderId(int orderId);

    DataResult<List<Order>> getOrdersCreatedAfter(Date date);


}
