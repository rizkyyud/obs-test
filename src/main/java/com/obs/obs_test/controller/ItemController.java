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
import com.obs.obs_test.service.ItemService;
import com.obs.obs_test.usecase.Item.CreateItemUseCase;
import com.obs.obs_test.usecase.Item.GetAllItemUseCase;
import com.obs.obs_test.usecase.Item.UpdateItemUseCase;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CreateItemUseCase createItemUseCase;

    @Autowired
    private GetAllItemUseCase getAllItemUseCase;

    @Autowired
    private UpdateItemUseCase updateItemUseCase;

    @GetMapping
    public Page<Item> getAllItem(Pageable pageable) {
        return getAllItemUseCase.execute(pageable);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return createItemUseCase.execute(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Item item = updateItemUseCase.execute(id, itemDetails);
        ApiResponse<Item> response = new ApiResponse<>("success", item, "Data Success Updated", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
