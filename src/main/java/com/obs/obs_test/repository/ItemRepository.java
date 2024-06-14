package com.obs.obs_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obs.obs_test.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);

    List<Item> findByName(String name);
}
