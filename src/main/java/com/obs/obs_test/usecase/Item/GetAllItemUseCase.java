package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Component
public class GetAllItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public Page<Item> execute(Pageable pageable) {

        try {
            return itemRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

}
