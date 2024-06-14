package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.service.ItemService;

@Component
public class GetAllItemUseCase {

    @Autowired
    private ItemService itemService;

    public Page<Item> execute(Pageable pageable) {
        return itemService.getAllItem(pageable);
    }

}
