package com.obs.obs_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.obs.obs_test.model.entity.Order;
import com.obs.obs_test.model.request.OrderRequest;
import com.obs.obs_test.usecase.Order.CreateOrderUseCase;
import com.obs.obs_test.usecase.Order.DeleteOrderUseCase;
import com.obs.obs_test.usecase.Order.GetAllOrderUseCase;
import com.obs.obs_test.usecase.Order.GetOrderByIdUseCase;
import com.obs.obs_test.usecase.Order.UpdateOrderUseCase;

@Service
public class OrderService {

    @Autowired
    private GetAllOrderUseCase getAllOrderUseCase;

    @Autowired
    private GetOrderByIdUseCase getOrderByIdUseCase;

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Autowired
    private UpdateOrderUseCase updateOrderUseCase;

    @Autowired
    private DeleteOrderUseCase deleteOrderUseCase;

    public Page<Order> getAllOrder(Pageable pageable) {
        return getAllOrderUseCase.execute(pageable);
    }

    public Order getOrderById(Long id) {
        return getOrderByIdUseCase.execute(id);
    }

    public Order createOrder(OrderRequest order) {
        return createOrderUseCase.execute(order);
    }

    public Order updateOrder(Long id, OrderRequest order) {
        return updateOrderUseCase.execute(id, order);
    }

    public void deleteOrder(Long id) {
        deleteOrderUseCase.execute(id);
    }
}
