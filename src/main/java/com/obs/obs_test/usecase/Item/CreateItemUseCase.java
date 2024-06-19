package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Component
public class CreateItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public Item execute(Item item) {

        if (item.getName().isEmpty()) {
            throw new BadRequestException("Name is mandatory");
        } else if (item.getPrice() == null || item.getPrice() < 0) {
            throw new BadRequestException("Price is mandatory and more than 0");
        } else if (itemRepository.existsByName(item.getName())) {
            throw new BadRequestException("Item with same name already exists");
        }

        return itemRepository.save(item);
    }
}
