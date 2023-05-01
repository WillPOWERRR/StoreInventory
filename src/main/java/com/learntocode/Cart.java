package com.learntocode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(items.keySet());
    }

    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }

    public double getTotal() {
        double total = 0;

        for (Product product : items.keySet()) {
            int quantity = items.get(product);
            double price = product.getPrice();
            total += quantity * price;
        }

        return total;
    }
}
