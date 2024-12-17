package com.agh.store.service;

import com.agh.store.model.*;
import com.sun.source.tree.Tree;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class CheckoutService {
    private Cart cart;
    private StoreProducts storeProducts;
    private DiscountRelations discountRelations;

    public void addToCart(long itemId, int quantity) {
        Optional<Item> item = storeProducts.getItemById(itemId);
        if (item.isPresent()) {
            cart.addItem(item.get(), quantity);
        } else {
            throw new IllegalArgumentException("Item with id '" + itemId + "' does not exist.");
        }
    }

    public TreeMap<Item, Integer> showCart() {
        return cart.getCart();
    }

    public String getPrice(long id) {
        Optional<Item> item = storeProducts.getItemById(id);
        if (item.isPresent()) {
            Optional<Integer> requiredQuantity = Optional.ofNullable(item.get().getRequiredQuantity());
            if (requiredQuantity.isPresent()) {
                return "Normal price " + item.get().getNormalPrice() + "PLN, special price " + item.get().getSpecialPrice() + "PLN with required quantity of " + item.get().getRequiredQuantity();
            } else {
                return "Normal price " + item.get().getNormalPrice() + "PLN";
            }
        } else {
            throw new IllegalArgumentException("Item with id '" + id + "' does not exist.");
        }
    }

    public double calculatePairDiscount() {
        double discount = 0;
        for (Item item1 : cart.getCart().keySet()) {
            for (Item item2 : cart.getCart().keySet()) {
                double curr_discount = 0;
                if (discountRelations.getDiscountRelations().containsKey(new DiscountKey(item1, item2))) {
                    curr_discount += discountRelations.getDiscountRelations().get(new DiscountKey(item1, item2));
                    curr_discount *= cart.getCart().get(item1);
                }
                discount += curr_discount;
            }
        }
        return discount;
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
        double discount = calculatePairDiscount();
        receipt.append("Discount from pair items: ").append(discount).append("PLN\n");
        receipt.append("Total price: ").append(totalPrice - discount).append("PLN");
        return receipt.toString();
    }
}
