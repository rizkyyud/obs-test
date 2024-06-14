package com.obs.obs_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.obs.obs_test.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
