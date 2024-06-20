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
public class UpdateInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Inventory execute(Long id, Inventory inventory) {
        Item existItem = itemRepository.findById(inventory.getItem().getId())
                .orElseThrow(() -> new BadRequestException("Item id not found"));
        Inventory existInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Inventory id not found"));

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

                existItem.setStock(stock);
                itemRepository.save(existItem);
                return inventoryRepository.save(inventorySave);
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }
        } else if (inventory.getType().equalsIgnoreCase("W")) {
            try {

                int stock = existItem.getStock() + (inventory.getQty() - existInventory.getQty());
                if (stock < 0) {
                    throw new BadRequestException("A quantity of that amount will cause minus stock");
                }

                Inventory inventorySave = new Inventory();
                inventorySave.setItem(existItem);
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
