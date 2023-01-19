package com.enoca.backendChallenge2.controllers;


import com.enoca.backendChallenge2.dtos.CreateOrderDto;
import com.enoca.backendChallenge2.dtos.UpdateOrderDto;
import com.enoca.backendChallenge2.models.Order;
import com.enoca.backendChallenge2.results.DataResult;
import com.enoca.backendChallenge2.results.Result;
import com.enoca.backendChallenge2.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersController {

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }
    private OrderService orderService;

    @PostMapping("/order/add")
    public Result create(@RequestBody CreateOrderDto order) {
        return this.orderService.create(order);
    }

    @PutMapping("/order/update/{id}")
    public Result update(@RequestBody UpdateOrderDto order, @PathVariable int id) {
        return this.orderService.update(order, id);
    }

    @DeleteMapping("/order/delete/{orderId}")
    public Result delete(@PathVariable int orderId) {
        return this.orderService.delete(orderId);
    }

    @GetMapping("/orders")
    public DataResult<List<Order>> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/order/getById/{orderId}")
    public Result getByOrderId(@PathVariable int orderId) {
        return this.orderService.getByOrderId(orderId);
    }


    @GetMapping("/orders/createdAfter")
    public DataResult<List<Order>> getOrdersCreatedAfter(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return this.orderService.getOrdersCreatedAfter(date);
    }
}
