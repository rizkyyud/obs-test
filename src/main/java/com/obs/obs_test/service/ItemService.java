package com.obs.obs_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.usecase.Item.CreateItemUseCase;
import com.obs.obs_test.usecase.Item.DeleteItemUseCase;
import com.obs.obs_test.usecase.Item.GetAllItemUseCase;
import com.obs.obs_test.usecase.Item.GetItemByIdUseCase;
import com.obs.obs_test.usecase.Item.UpdateItemUseCase;

@Service
public class ItemService {

    @Autowired
    private CreateItemUseCase createItemUseCase;

    @Autowired
    private GetAllItemUseCase getAllItemUseCase;

    @Autowired
    private UpdateItemUseCase updateItemUseCase;

    @Autowired
    private GetItemByIdUseCase getItemByIdUseCase;

    @Autowired
    private DeleteItemUseCase deleteItemUseCase;

    public Page<Item> getAllItem(Pageable pageable) {
        return getAllItemUseCase.execute(pageable);
    }

    public Item getItemById(Long id) {
        return getItemByIdUseCase.execute(id);
    }

    public Item createItem(Item item) {
        return createItemUseCase.execute(item);
    }

    public Item updateItem(Long id, Item itemData) {
        return updateItemUseCase.execute(id, itemData);
    }

    public void deleteItem(Long id) {
        deleteItemUseCase.execute(id);
    }
}
