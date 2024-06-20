package com.obs.obs_test.usecase.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.repository.InventoryRepository;

@Component
public class GetInventoryByIdUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory execute(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory id not found"));
    }
}
