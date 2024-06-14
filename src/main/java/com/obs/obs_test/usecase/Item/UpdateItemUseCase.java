package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.service.ItemService;

@Component
public class UpdateItemUseCase {

    @Autowired
    private ItemService itemService;

    public Item execute(Long id, Item item) {

        Optional<Item> existItem = itemService.getItemById(id);
        if (!existItem.isPresent()) {
            new BadRequestException("Id item not found ");
        }

        return itemService.updateItem(id, item, existItem.get());
    }
}
