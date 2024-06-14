package com.obs.obs_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obs.obs_test.model.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
