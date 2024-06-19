package com.obs.obs_test.usecase.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obs.obs_test.repository.ItemRepository;

@Component
public class DeleteItemUseCase {

    @Autowired
    private ItemRepository itemRepository;

    public void execute(Long id) {
        itemRepository.deleteById(id);
    }
}
