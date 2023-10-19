package com.adekg.WebShop.service;

import com.adekg.WebShop.Cart;
import com.adekg.WebShop.datatransferobject.OrderDto;
import com.adekg.WebShop.mapper.OrderMapper;
import com.adekg.WebShop.model.order.Order;
import com.adekg.WebShop.repository.order.OrderItemRepository;
import com.adekg.WebShop.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.clearCart();
    }
}
