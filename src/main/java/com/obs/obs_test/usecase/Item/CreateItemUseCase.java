package com.obs.obs_test.usecase.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.model.request.ItemRequest;
import com.obs.obs_test.repository.ItemRepository;
import com.obs.obs_test.usecase.ValidatorUseCase;

import jakarta.transaction.Transactional;

@Component
public class CreateItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ValidatorUseCase validatorUseCase;

    private static final Logger logger = LoggerFactory.getLogger(CreateItemUseCase.class);

    @Transactional
    public Item execute(ItemRequest item) {

        validatorUseCase.execute(item);
        if (itemRepository.existsByName(item.getName())) {
            throw new BadRequestException("Item with same name already exists");
        }

        try {
            Item itemSave = new Item();
            itemSave.setName(item.getName());
            itemSave.setPrice(item.getPrice());
            itemRepository.save(itemSave);

            logger.info("Item created successfully with id: {}", itemSave.getId());
            return itemSave;
        } catch (Exception e) {
            logger.error("Failed to create item: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
