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


    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();
        double totalPrice = 0;

        for (Item item : cart.getCart().keySet()) {
            Optional<Integer> requiredQuantity = Optional.ofNullable(item.getRequiredQuantity());
            double price;

            if (requiredQuantity.isPresent() && item.getRequiredQuantity() <= cart.getCart().get(item)) {
                price = item.getSpecialPrice() * cart.getCart().get(item);
                receipt.append(item.getName()).append(" x ").append(cart.getCart().get(item)).append(" = ").append(price).append("PLN\n");
            } else {
                price = item.getNormalPrice() * cart.getCart().get(item);
                receipt.append(item.getName()).append(" x ").append(cart.getCart().get(item)).append(" = ").append(price).append("PLN\n");
            }

            totalPrice += price;

        }
        receipt.append("Total price: ").append(totalPrice).append("PLN");
        return receipt.toString();
    }
}
