package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Component
public class UpdateItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    private static final Logger logger = LoggerFactory.getLogger(UpdateItemUseCase.class);

    @Transactional
    public Item execute(Long id, Item item) {
        Optional<Item> existItem = itemRepository.findById(id);
        if (!existItem.isPresent()) {
            new BadRequestException("Id item not found ");
        }

        try {
            existItem.get().setName(item.getName());
            existItem.get().setPrice(item.getPrice());
            Item save = itemRepository.save(existItem.get());
            logger.info("Item update successfully with id: {}", item.getId());
            return save;
        } catch (Exception e) {
            logger.error("Failed to update item: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());

        }
    }
}
