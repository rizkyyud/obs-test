package com.obs.obs_test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.repository.InventoryRepository;
import com.obs.obs_test.usecase.Inventory.GetAllInventoryUseCase;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private GetAllInventoryUseCase getAllInventoryUseCase;

    public Page<Inventory> getAllInventory(Pageable pageable) {
        return getAllInventoryUseCase.execute(pageable);
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    @Transactional
    public Inventory creatInventory(Inventory inventory) {

        return inventoryRepository.save(inventory);
    }
}
