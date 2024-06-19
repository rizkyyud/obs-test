package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Component
public class GetItemByIdUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public Item execute(Long id) {
        Item itemS = itemRepository.findById(id).orElseThrow(() -> new BadRequestException("Id item not found"));
        return itemS;
    }

}
