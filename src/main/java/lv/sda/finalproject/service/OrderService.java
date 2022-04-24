package lv.sda.finalproject.service;

import lv.sda.finalproject.model.Order;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {

    Order save(Order order);
    void addProduct(Long orderId, Long productId);
    void removeProduct(Long orderId, Long productId);
    void update(Order order);
    Collection<Order> findAllOrders();
    Optional<Order> findById(Long id);
}
