package com.agh.store.service;

import com.agh.store.model.Cart;
import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckoutService {
    private Cart cart;
    private StoreProducts storeProducts;

    public void addToCart(long itemId, int quantity) {
        Optional<Item> item = storeProducts.getItemById(itemId);
        if (item.isPresent()) {
            cart.addItem(item.get(), quantity);
        } else {
            throw new IllegalArgumentException("Item with id '" + itemId + "' does not exist.");
        }
    }

    public HashMap<Item, Integer> showCart() {
        return cart.getCart();
    }

    public double checkout() {
        double totalPrice = 0;
        for (Item item : cart.getCart().keySet()) {
            Optional<Integer> requiredQuantity = Optional.ofNullable(item.getRequiredQuantity());

            if (requiredQuantity.isPresent() && item.getRequiredQuantity() <= cart.getCart().get(item)) {
                totalPrice += item.getSpecialPrice() * cart.getCart().get(item);
            } else {
                totalPrice += item.getNormalPrice() * cart.getCart().get(item);
            }
        }
        return totalPrice;
    }
}
