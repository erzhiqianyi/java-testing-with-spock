package top.erzhiqian.spock.sample.eshop;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<Product, Integer> contents = new HashMap<>();

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, int times) {
        if (contents.containsKey(product)) {
            int existing = contents.get(product);
            contents.put(product, times + existing);
        } else {
            contents.put(product, times);
        }
    }

    public int getCurrentWeight() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : contents.entrySet()) {
            total = total + (entry.getKey().getWeight() * entry.getValue());
        }

        return total;
    }

    public int getProductTypesCount() {
        return contents.size();
    }

    public void clearAllProducts() {
        contents.clear();
    }

}
