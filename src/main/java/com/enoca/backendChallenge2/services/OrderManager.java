package com.enoca.backendChallenge2.services;

import com.enoca.backendChallenge2.dtos.CreateOrderDto;
import com.enoca.backendChallenge2.dtos.UpdateOrderDto;
import com.enoca.backendChallenge2.models.Order;
import com.enoca.backendChallenge2.repos.OrderRepo;
import com.enoca.backendChallenge2.results.DataResult;
import com.enoca.backendChallenge2.results.Result;
import com.enoca.backendChallenge2.results.SuccessDataResult;
import com.enoca.backendChallenge2.results.SuccessResult;
import org.aspectj.weaver.ast.Or;
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
        return null;
    }

    @Override
    public DataResult<List<Order>> getAll() {
        return null;
    }

    @Override
    public DataResult<Order> getByOrderId(int orderId) {
        return null;
    }

    @Override
    public DataResult<List<Order>> getOrdersCreatedAfter(Date date) {
        List<Order> orders = this.orderRepo.findByCreateDateAfter(date);
        return new SuccessDataResult<List<Order>>(orders, "Orders listed");
    }

}
