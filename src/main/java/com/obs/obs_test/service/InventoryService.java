package com.obs.obs_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.model.request.InventoryRequest;
import com.obs.obs_test.usecase.Inventory.CreateInventoryUseCase;
import com.obs.obs_test.usecase.Inventory.DeleteInventoryUseCase;
import com.obs.obs_test.usecase.Inventory.GetAllInventoryUseCase;
import com.obs.obs_test.usecase.Inventory.GetInventoryByIdUseCase;
import com.obs.obs_test.usecase.Inventory.UpdateInventoryUseCase;

@Service
public class InventoryService {

    @Autowired
    private GetAllInventoryUseCase getAllInventoryUseCase;

    @Autowired
    private GetInventoryByIdUseCase getInventoryByIdUseCase;

    @Autowired
    private CreateInventoryUseCase createInventoryUseCase;

    @Autowired
    private UpdateInventoryUseCase updateInventoryUseCase;

    @Autowired
    private DeleteInventoryUseCase deleteInventoryUseCase;

    public Page<Inventory> getAllInventory(Pageable pageable) {
        return getAllInventoryUseCase.execute(pageable);
    }

    public Inventory getInventoriesById(Long id) {
        return getInventoryByIdUseCase.execute(id);
    }

    public Inventory createInventory(InventoryRequest inventory) {
        return createInventoryUseCase.execute(inventory);
    }

    public Inventory updateInventory(Long id, InventoryRequest inventory) {
        return updateInventoryUseCase.execute(id, inventory);
    }

    public void deleteInventory(Long id) {
        deleteInventoryUseCase.execute(id);
    }

}
