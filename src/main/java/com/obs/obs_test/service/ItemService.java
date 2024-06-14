package com.obs.obs_test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.obs_test.model.entity.Item;
import com.obs.obs_test.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Page<Item> getAllItem(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public boolean existsByName(String name) {
        return itemRepository.existsByName(name);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item itemRequest, Item itemData) {
        itemData.setName(itemRequest.getName());
        itemData.setPrice(itemRequest.getPrice());
        return itemRepository.save(itemData);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
