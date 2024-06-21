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
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.model.request.ItemRequest;
import com.obs.obs_test.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Item>>> getAllItem(Pageable pageable) {
        Page<Item> itemPage = itemService.getAllItem(pageable);
        ApiResponse<Page<Item>> response = new ApiResponse<>("success", itemPage, "Data Success Get",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> getItemById(Long id) {
        Item item = itemService.getItemById(id);
        ApiResponse<Item> response = new ApiResponse<>("success", item, "Data Success Get",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Item>> createItem(@RequestBody ItemRequest item) {
        Item saveItem = itemService.createItem(item);
        ApiResponse<Item> response = new ApiResponse<>("success", saveItem, "Data Success Added",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> updateItem(@PathVariable Long id, @RequestBody ItemRequest itemDetails) {
        Item item = itemService.updateItem(id, itemDetails);
        ApiResponse<Item> response = new ApiResponse<>("success", item, "Data Success Updated", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        ApiResponse<Item> response = new ApiResponse<>("success", null, "Data Success Delete", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
