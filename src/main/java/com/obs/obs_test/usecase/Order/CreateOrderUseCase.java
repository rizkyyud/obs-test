package com.obs.obs_test.usecase.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.model.entity.Order;
import com.obs.obs_test.model.request.OrderRequest;
import com.obs.obs_test.repository.ItemRepository;
import com.obs.obs_test.repository.OrderRepository;
import com.obs.obs_test.usecase.ValidatorUseCase;

import jakarta.transaction.Transactional;

@Component
public class CreateOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ValidatorUseCase validatorUseCase;

    private static final Logger logger = LoggerFactory.getLogger(CreateOrderUseCase.class);

    @Transactional
    public Order execute(OrderRequest orderRequest) {

        validatorUseCase.execute(orderRequest);
        Item existItem = itemRepository.findById(orderRequest.getItem())
                .orElseThrow(() -> new ResourceNotFoundException("Item id not found"));
        try {
            if (orderRequest.getQty() > existItem.getStock()) {
                throw new BadRequestException("Stock item lower then withdraw Quantity");
            }

            Order orderSave = new Order();
            orderSave.setItem(existItem);
            orderSave.setQty(orderRequest.getQty());
            orderRepository.save(orderSave);

            orderSave.setOrderNo("O" + orderSave.getId());
            orderRepository.save(orderSave);

            existItem.setStock(orderRequest.getQty() - existItem.getStock());
            itemRepository.save(existItem);
            logger.info("Order created successfully with id: {}", orderSave.getId());
            return orderSave;
        } catch (Exception e) {
            logger.error("Failed create order: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }

    }
}
