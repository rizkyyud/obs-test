package com.obs.obs_test.usecase.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.repository.InventoryRepository;

@Component
public class GetInventoryByIdUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory execute(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Inventory id not found"));
        return inventory;
    }
}
