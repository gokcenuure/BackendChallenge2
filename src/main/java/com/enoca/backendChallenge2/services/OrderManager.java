package com.enoca.backendChallenge2.services;

import com.enoca.backendChallenge2.dtos.CreateOrderDto;
import com.enoca.backendChallenge2.dtos.UpdateOrderDto;
import com.enoca.backendChallenge2.exceptions.OrderNotFoundException;
import com.enoca.backendChallenge2.exceptions.ResourceNotFoundException;
import com.enoca.backendChallenge2.models.Order;
import com.enoca.backendChallenge2.repos.OrderRepo;
import com.enoca.backendChallenge2.results.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderManager implements OrderService{

    @Autowired
    public OrderManager(ModelMapper modelMapper, OrderRepo orderRepo) {
        this.modelMapper = modelMapper;
        this.orderRepo = orderRepo;
    }

    private OrderRepo orderRepo;
    private ModelMapper modelMapper;



    @Override
    public Result create(CreateOrderDto orderDto) {
        Order orderToCreate = modelMapper.map(orderDto, Order.class);
        this.orderRepo.save(orderToCreate);
        return new SuccessResult("Order added");
    }

    @Override
    public Result update(UpdateOrderDto orderDto, int id) {
        Order UpdateOrderDto = modelMapper.map(orderDto, Order.class);
        Order orderToUpdate = this.getByOrderId(id).getData();

        orderToUpdate.setCreateDate(orderDto.getCreateDate());

        orderRepo.save(orderToUpdate);
        return new SuccessResult("Order updated");
    }

    @Override
    public Result delete(int orderId) {
        Order order = this.orderRepo.getById(orderId);
        orderRepo.delete(order);
        return new SuccessResult("Order deleted");
    }
    @Override
    public DataResult<List<Order>> getAll() {
        return new SuccessDataResult<List<Order>>
        (this.orderRepo.findAll(),"Orders listed");
    }

    @Override
    public DataResult<Order> getByOrderId(int orderId) {
        try {
            Order order = this.orderRepo.findById(orderId).orElse(null);
            if (order==null) {
                throw new OrderNotFoundException("Invalid order id");
            }
            return new SuccessDataResult<Order>(order,"Order found");
        } catch (OrderNotFoundException ex) {
            return new ErrorDataResult("Error: " + ex.getMessage());
        }
    }

    @Override
    public DataResult<List<Order>> getOrdersCreatedAfter(Date date) {
        List<Order> orders = this.orderRepo.findByCreateDateAfter(date);
        return new SuccessDataResult<List<Order>>(orders, "Orders listed");
    }

}
