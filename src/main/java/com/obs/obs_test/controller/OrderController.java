package com.obs.obs_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obs.obs_test.exception.ApiResponse;
import com.obs.obs_test.model.entity.Order;
import com.obs.obs_test.model.request.OrderRequest;
import com.obs.obs_test.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    private ResponseEntity<ApiResponse<Page<Order>>> getAllOrder(Pageable pageable) {
        Page<Order> orderPage = orderService.getAllOrder(pageable);
        ApiResponse<Page<Order>> response = new ApiResponse<>("success", orderPage, "Data Success Get",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Order>> getOrderById(Long id) {
        Order order = orderService.getOrderById(id);
        ApiResponse<Order> response = new ApiResponse<>("success", order, "Data Success Get",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createItem(@RequestBody OrderRequest orderRequest) {
        Order saveOrder = orderService.createOrder(orderRequest);
        ApiResponse<Order> response = new ApiResponse<>("success", saveOrder, "Data Success Added",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> updateItem(@PathVariable Long id,
            @RequestBody OrderRequest orderRequest) {
        Order order = orderService.updateOrder(id, orderRequest);
        ApiResponse<Order> response = new ApiResponse<>("success", order, "Data Success Updated",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> deleteItem(@PathVariable Long id) {
        orderService.deleteOrder(id);
        ApiResponse<Order> response = new ApiResponse<>("success", null, "Data Success Delete",
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
