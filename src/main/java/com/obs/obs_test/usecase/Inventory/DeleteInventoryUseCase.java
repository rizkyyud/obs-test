package com.obs.obs_test.usecase.Inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.InventoryRepository;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Component
public class DeleteInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeleteInventoryUseCase.class);

    @Transactional
    public void execute(Long id) {
        Inventory existInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory id not found"));
        Item existItem = itemRepository.findById(existInventory.getItem().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Item id not found"));

        try {
            if (existItem.getStock() - existInventory.getQty() < 0 && existInventory.getType().equalsIgnoreCase("T")) {
                throw new BadRequestException("Delete this inventory will cause minus stock");
            }

            inventoryRepository.deleteById(id);
            existItem.setStock(existItem.getStock() + existInventory.getQty());
            itemRepository.save(existItem);
            logger.info("Inventory delete successfully with id: {}", id);
        } catch (Exception e) {
            logger.error("Failed delete inventory: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
