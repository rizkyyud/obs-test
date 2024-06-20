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
public class UpdateInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ValidatorUseCase validatorUseCase;

    private static final Logger logger = LoggerFactory.getLogger(UpdateInventoryUseCase.class);

    @Transactional
    public Inventory execute(Long id, InventoryRequest inventory) {
        validatorUseCase.execute(inventory);
        Item existItem = itemRepository.findById(inventory.getItem())
                .orElseThrow(() -> new ResourceNotFoundException("Item id not found"));
        Inventory existInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory id not found"));

        if (inventory.getType().equalsIgnoreCase("T")) {
            try {

                int stock = existItem.getStock() + (inventory.getQty() - existInventory.getQty());
                if (stock < 0) {
                    throw new BadRequestException("A quantity of that amount will cause minus stock");
                }

                Inventory inventorySave = new Inventory();
                inventorySave.setItem(existItem);
                inventorySave.setQty(inventory.getQty());
                inventorySave.setType("T");
                inventoryRepository.save(inventorySave);

                existItem.setStock(stock);
                itemRepository.save(existItem);
                logger.info("Inventory update successfully with id: {}", inventorySave.getId());
                return inventorySave;
            } catch (Exception e) {
                logger.error("Failed update inventory: {}", e.getMessage());
                throw new BadRequestException(e.getMessage());
            }
        } else if (inventory.getType().equalsIgnoreCase("W")) {
            try {

                int stock = existItem.getStock() - (inventory.getQty() - existInventory.getQty());
                if (stock < 0) {
                    throw new BadRequestException("A quantity of that amount will cause minus stock");
                }

                Inventory inventorySave = new Inventory();
                inventorySave.setItem(existItem);
                inventorySave.setQty(inventory.getQty());
                inventorySave.setType("W");
                inventoryRepository.save(inventorySave);

                existItem.setStock(stock);
                itemRepository.save(existItem);
                logger.info("Inventory update successfully with id: {}", inventorySave.getId());
                return inventorySave;
            } catch (Exception e) {
                logger.error("Failed update inventory: {}", e.getMessage());
                throw new BadRequestException(e.getMessage());
            }
        } else {
            throw new BadRequestException("Type Must T or W");
        }
    }
}
