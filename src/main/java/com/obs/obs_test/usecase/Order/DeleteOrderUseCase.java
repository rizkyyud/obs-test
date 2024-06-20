package com.obs.obs_test.usecase.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.model.entity.Order;
import com.obs.obs_test.repository.ItemRepository;
import com.obs.obs_test.repository.OrderRepository;
import com.obs.obs_test.usecase.Inventory.DeleteInventoryUseCase;

import jakarta.transaction.Transactional;

@Component
public class DeleteOrderUseCase {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeleteInventoryUseCase.class);

    @Transactional
    public void execute(Long id) {

        Order existOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item id not found"));
        Item existItem = itemRepository.findById(existOrder.getItem().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item id not found"));

        try {
            if (existItem.getStock() - existOrder.getQty() < 0) {
                throw new BadRequestException("Delete this inventory will cause minus stock");
            }

            orderRepository.deleteById(id);
            existItem.setStock(existItem.getStock() + existOrder.getQty());
            itemRepository.save(existItem);
            logger.info("Order delete successfully with id: {}", id);
        } catch (Exception e) {
            logger.error("Failed delete order: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
