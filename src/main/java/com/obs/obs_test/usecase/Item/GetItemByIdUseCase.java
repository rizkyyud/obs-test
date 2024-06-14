package com.obs.obs_test.usecase.Item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.exception.BadRequestException;
import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.service.ItemService;

@Component
public class GetItemByIdUseCase {

    @Autowired
    private ItemService itemService;

    public Item execute(Long id) {
        Optional<Item> itemS = itemService.getItemById(id);
        return itemS.orElseThrow(() -> new BadRequestException("Id item not found"));
    }

}
