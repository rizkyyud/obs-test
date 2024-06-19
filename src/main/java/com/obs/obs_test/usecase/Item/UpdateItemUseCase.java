package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Component
public class UpdateItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public Item execute(Long id, Item item) {

        Optional<Item> existItem = itemRepository.findById(id);
        if (!existItem.isPresent()) {
            new BadRequestException("Id item not found ");
        }
        existItem.get().setName(item.getName());
        existItem.get().setPrice(item.getPrice());
        return itemRepository.save(existItem.get());
    }
}
