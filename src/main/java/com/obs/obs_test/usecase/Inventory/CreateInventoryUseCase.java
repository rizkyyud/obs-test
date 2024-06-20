package com.obs.obs_test.usecase.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.InventoryRepository;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Component
public class CreateInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Inventory execute(Inventory inventory) {
        Item existItem = itemRepository.findById(inventory.getItemId())
                .orElseThrow(() -> new BadRequestException("Item id not found"));
        if (inventory.getType().equalsIgnoreCase("T")) {
            try {

                Inventory inventorySave = new Inventory();
                inventorySave.setItemId(inventory.getItemId());
                inventorySave.setQty(inventory.getQty());
                inventorySave.setType("T");

                existItem.setStock(inventory.getQty() + existItem.getStock());
                itemRepository.save(existItem);

                return inventoryRepository.save(inventorySave);
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
        } else if (inventory.getType().equalsIgnoreCase("W")) {
            try {
                if (inventory.getQty() > existItem.getStock()) {
                    throw new BadRequestException("Stock item lower then withdraw Quantity");
                }

                Inventory inventorySave = new Inventory();
                inventorySave.setItemId(inventory.getItemId());
                inventorySave.setQty(inventory.getQty());
                inventorySave.setType("W");

                existItem.setStock(inventory.getQty() - existItem.getStock());
                itemRepository.save(existItem);

                return inventoryRepository.save(inventorySave);
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
        } else {
            throw new BadRequestException("Type Must T or W");
        }
    }
}
