package com.adekg.WebShop;

import com.adekg.WebShop.model.Item;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> itemsInCart = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> itemsInCart.add(new CartItem(item))
        );
        recalculatePriceAndCounter();
    }

    public void decreaseItem(Item item) {
        Optional<CartItem> oCartItem = getCartItemByItem(item);
        if(oCartItem.isPresent()) {
            CartItem cartItem = oCartItem.get();
            cartItem.decreaseCounter();
            if(cartItem.hasNoItems()) {
                removeAllItems(item);
            } else {
                recalculatePriceAndCounter();
            }
        }
    }
    public void removeAllItems(Item item) {
        itemsInCart.removeIf(i -> i.idEquals(item));
        recalculatePriceAndCounter();
    }

    private void recalculatePriceAndCounter() {
        sum = itemsInCart.stream().map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = itemsInCart.stream().mapToInt(CartItem::getCounter)
                .reduce(0, Integer::sum);
    }

    private Optional<CartItem> getCartItemByItem(Item item) {
        return itemsInCart.stream()
                .filter(i -> i.idEquals(item))
                .findFirst();
    }

    public void clearCart() {
        itemsInCart.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }
}
