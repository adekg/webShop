package com.adekg.WebShop.mapper;

import com.adekg.WebShop.Cart;
import com.adekg.WebShop.CartItem;
import com.adekg.WebShop.datatransferobject.OrderDto;
import com.adekg.WebShop.model.order.Order;
import com.adekg.WebShop.model.order.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .orderTime(LocalDateTime.now())
                .build();
    }

    public static List<OrderItem> mapToOrderItemList(Cart cart, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem iic : cart.getItemsInCart()) {
            orderItems.add(new OrderItem(order.getOrderId(), iic.getItem().getId(), iic.getCounter()));
        }
        return orderItems;
    }
}
