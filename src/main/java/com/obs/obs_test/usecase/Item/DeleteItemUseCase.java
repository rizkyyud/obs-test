package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

import jakarta.transaction.Transactional;

@Component
public class DeleteItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    private static final Logger logger = LoggerFactory.getLogger(DeleteItemUseCase.class);

    @Transactional
    public void execute(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        try {
            if (item.isPresent()) {
                itemRepository.deleteById(id);
                logger.info("Item delete successfully with id: {}", id);
            } else {
                throw new ResourceNotFoundException("Item id not found");
            }
        } catch (Exception e) {
            logger.error("Failed delete item with id: {}", id);
            throw new BadRequestException(e.getMessage());
        }

    }
}
