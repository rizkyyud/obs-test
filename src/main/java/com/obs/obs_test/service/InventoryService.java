package com.obs.obs_test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.InventoryRepository;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Transactional
    public Inventory creatInventory(Inventory inventory) {
        Item item = itemRepository.findById(inventory.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item ID not found"));

        if (inventory.getType() == 'T') {
            item.setStock(item.getStock() + inventory.getQty());
        } else if (inventory.getType() == 'W') {
            if (item.getStock() < inventory.getQty()) {
                throw new RuntimeException("Stock not sufficient");
            }
            item.setStock(item.getStock() - inventory.getQty());
        }
        itemRepository.save(item);
        return inventoryRepository.save(inventory);
    }
}
