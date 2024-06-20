package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.ResourceNotFoundException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Component
public class GetItemByIdUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public Item execute(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id item not found"));
    }

}
