package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.service.ItemService;

@Component
public class CreateItemUseCase {

    @Autowired
    private ItemService itemService;

    public Item execute(Item item) {

        if (item.getName().isEmpty()) {
            throw new BadRequestException("Name is mandatory");
        } else if (item.getPrice() == null || item.getPrice() > 0) {
            throw new BadRequestException("Price is mandatory and more than 0");
        } else if (itemService.existsByName(item.getName())) {
            throw new BadRequestException("Item with same name already exists");
        }

        return itemService.createItem(item);
    }
}
