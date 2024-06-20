package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Component
public class DeleteItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void execute(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        try {
            if (item.isPresent()) {
                itemRepository.deleteById(id);
            } else {
                new BadRequestException("Item id not found");
            }
        } catch (Exception e) {
            new BadRequestException(e.getMessage());
        }

    }
}
