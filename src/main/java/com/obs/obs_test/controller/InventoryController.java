package com.obs.obs_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obs.obs_test.exception.ApiResponse;
import com.obs.obs_test.model.entity.Inventory;
import com.obs.obs_test.model.request.InventoryRequest;
import com.obs.obs_test.service.InventoryService;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    private ResponseEntity<ApiResponse<Page<Inventory>>> getAllInventory(Pageable pageable) {
        Page<Inventory> invenPage = inventoryService.getAllInventory(pageable);
        ApiResponse<Page<Inventory>> response = new ApiResponse<>("success", invenPage, "Data Success Get",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Inventory>> getInventoryById(Long id) {
        Inventory inventory = inventoryService.getInventoriesById(id);
        ApiResponse<Inventory> response = new ApiResponse<>("success", inventory, "Data Success Get",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Inventory>> createItem(@RequestBody InventoryRequest inventory) {
        Inventory saveInventory = inventoryService.createInventory(inventory);
        ApiResponse<Inventory> response = new ApiResponse<>("success", saveInventory, "Data Success Added",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventory>> updateItem(@PathVariable Long id,
            @RequestBody InventoryRequest inventoryData) {
        Inventory inventory = inventoryService.updateInventory(id, inventoryData);
        ApiResponse<Inventory> response = new ApiResponse<>("success", inventory, "Data Success Updated",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Inventory>> deleteItem(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        ApiResponse<Inventory> response = new ApiResponse<>("success", null, "Data Success Delete",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
