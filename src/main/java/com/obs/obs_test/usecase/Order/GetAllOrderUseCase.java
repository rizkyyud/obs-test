package com.obs.obs_test.usecase.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Order;
import com.obs.obs_test.repository.OrderRepository;

@Component
public class GetAllOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> execute(Pageable pageable) {
        try {

            return orderRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
