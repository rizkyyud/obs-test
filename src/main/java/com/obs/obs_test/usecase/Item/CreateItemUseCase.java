package com.obs.obs_test.usecase.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Component
public class CreateItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeleteItemUseCase.class);

    @Transactional
    public Item execute(Item item) {

        if (item.getName().isEmpty()) {
            throw new BadRequestException("Name is mandatory");
        } else if (item.getPrice() == null || item.getPrice() < 0) {
            throw new BadRequestException("Price is mandatory and more than 0");
        } else if (itemRepository.existsByName(item.getName())) {
            throw new BadRequestException("Item with same name already exists");
        }

        try {
            Item save = itemRepository.save(item);
            logger.info("Item created successfully with id: {}", item.getId());
            return save;
        } catch (Exception e) {
            logger.error("Failed to create item: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
