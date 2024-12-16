package com.agh.store.configurator;

import com.agh.store.model.Item;
import com.agh.store.model.StoreProducts;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StoreConfigurator {
    private StoreProducts storeProducts;

    @PostConstruct
    public void init() {
        if(storeProducts.getItems().isEmpty()) {
            Item item1 = Item.builder()
                    .name("Truck")
                    .normalPrice(15.00)
                    .requiredQuantity(4)
                    .specialPrice(12.00)
                    .build();

            Item item2 = Item.builder()
                    .name("Teddy")
                    .normalPrice(5.00)
                    .requiredQuantity(2)
                    .specialPrice(4.50)
                    .build();

            Item item3 = Item.builder()
                    .name("Toy")
                    .normalPrice(10.00)
                    .build();

            storeProducts.addItem(item1);
            storeProducts.addItem(item2);
            storeProducts.addItem(item3);

        }
    }
}
