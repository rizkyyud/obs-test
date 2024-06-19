package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Component
public class DeleteItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public void execute(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        if (item.isPresent()) {
            itemRepository.deleteById(id);
        } else {
            throw new BadRequestException("Item id not found");
        }

    }
}
