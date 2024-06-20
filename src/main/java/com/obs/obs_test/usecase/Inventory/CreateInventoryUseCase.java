package com.obs.obs_test.usecase.Inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.model.request.InventoryRequest;
import com.obs.obs_test.repository.InventoryRepository;
import com.obs.obs_test.repository.ItemRepository;
import com.obs.obs_test.usecase.ValidatorUseCase;

import jakarta.transaction.Transactional;

@Component
public class CreateInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ValidatorUseCase validatorUseCase;

    private static final Logger logger = LoggerFactory.getLogger(CreateInventoryUseCase.class);

    @Transactional
    public Inventory execute(InventoryRequest inventory) {

        validatorUseCase.execute(inventory);
        Item existItem = itemRepository.findById(inventory.getItem())
                .orElseThrow(() -> new ResourceNotFoundException("Item id not found"));
        if (inventory.getType().equalsIgnoreCase("T")) {
            try {

                Inventory inventorySave = new Inventory();
                inventorySave.setItem(existItem);
                inventorySave.setQty(inventory.getQty());
                inventorySave.setType("T");

                existItem.setStock(inventory.getQty() + existItem.getStock());
                itemRepository.save(existItem);

                logger.info("Inventory created successfully with id: {}", inventorySave.getId());
                return inventoryRepository.save(inventorySave);
            } catch (Exception e) {
                logger.error("Failed create inventory: {}", e.getMessage());
                throw new BadRequestException(e.getMessage());
            }
        } else if (inventory.getType().equalsIgnoreCase("W")) {
            try {
                if (inventory.getQty() > existItem.getStock()) {
                    throw new BadRequestException("Stock item lower then withdraw Quantity");
                }

                Inventory inventorySave = new Inventory();
                inventorySave.setItem(existItem);
                inventorySave.setQty(inventory.getQty());
                inventorySave.setType("W");

                existItem.setStock(inventory.getQty() - existItem.getStock());
                itemRepository.save(existItem);
                logger.info("Inventory created successfully with id: {}", inventorySave.getId());
                return inventoryRepository.save(inventorySave);
            } catch (Exception e) {
                logger.error("Failed create inventory: {}", e.getMessage());
                throw new BadRequestException(e.getMessage());
            }
        } else {
            throw new BadRequestException("Type Must T or W");
        }
    }
}
