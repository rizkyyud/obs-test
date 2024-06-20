package com.obs.obs_test.usecase.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.repository.InventoryRepository;

@Component
public class GetAllInventoryUseCase {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Page<Inventory> execute(Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }
}
