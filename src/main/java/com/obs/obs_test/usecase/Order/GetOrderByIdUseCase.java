package com.obs.obs_test.usecase.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Order;
import com.obs.obs_test.repository.OrderRepository;

@Component
public class GetOrderByIdUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public Order execute(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order id not found"));
    }
}
