package lv.sda.finalproject.service;

import lv.sda.finalproject.model.Order;
import lv.sda.finalproject.model.OrderItem;
import lv.sda.finalproject.model.Product;
import lv.sda.finalproject.repo.OrderItemRepository;
import lv.sda.finalproject.repo.OrderRepository;
import lv.sda.finalproject.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void addProduct(Long orderId, Long productId){
        Product foundProduct = productRepository.findById(productId)
                .orElse(productRepository.findAll().stream().findFirst().orElse(null));

        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        OrderItem item = new OrderItem();
        item.setOrder(foundOrder);
        item.setName(foundProduct.getProductName());
        item.setProduct(foundProduct);
        item.setPrice(foundProduct.getPrice());
        item.setQuantity(1);
        orderItemRepository.save(item);

    }

    @Override
    public void removeProduct(Long orderId, Long productId){
        Order foundOrder = orderRepository.findById(orderId).orElse(null);

        if (foundOrder != null) {
            foundOrder.getOrderedItems().stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findAny().ifPresent(item -> orderItemRepository.delete(item));
        }
    }

    @Override
    public void update(Order order){

        orderRepository.save(order);

    }

    @Override
    public Collection<Order> findAllOrders(){return orderRepository.findAll();}

    @Override
    public Order save(Order order){ return  orderRepository.save(order);}

    @Override
    public Optional<Order> findById(Long id){ return orderRepository.findById(id);}


}
