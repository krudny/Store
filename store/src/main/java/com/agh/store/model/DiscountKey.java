package com.agh.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class DiscountKey {
    private final Item item1;
    private final Item item2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountKey that = (DiscountKey) o;

        return item1.getItemId() == that.item1.getItemId() &&
                item2.getItemId() == that.item2.getItemId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(item1.getItemId(), item2.getItemId());
    }

    @Override
    public String toString() {
        return "Buy " + item1.getName() + " and " + item2.getName() + " to get ";
    }

}
