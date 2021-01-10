package top.erzhiqian.spock.sample.eshop;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    protected Map<Product, Integer> contents = new HashMap<>();

    private ShippingCalculator shippingCalculator;

    private WarehouseInventory warehouseInventory;


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

    public int getCurrentPrice() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : contents.entrySet()) {
            total = total + (entry.getKey().getPrice() * entry.getValue());
        }
        return total;
    }

    public int getProductTypesCount() {
        return contents.size();
    }

    public void clearAllProducts() {
        contents.clear();
    }


    public void setShippingCalculator(ShippingCalculator shippingCalculator) {
        this.shippingCalculator = shippingCalculator;
    }

    public void setWarehouseInventory(WarehouseInventory warehouseInventory) {
        this.warehouseInventory = warehouseInventory;
    }


    public boolean canShipCompletely() {
        if (warehouseInventory.isEmpty()) {
            return false;
        }

        return contents.entrySet()
                .stream()
                .allMatch(entry ->
                        warehouseInventory.isProductAvailable(
                                entry.getKey().getName(), entry.getValue())
                );
    }

    public int findTotalCost() {
        int sum = contents.entrySet()
                .stream()
                .mapToInt(entry -> countShippingCost(entry.getKey(), entry.getValue()))
                .sum();
        return sum;
    }

    private int countShippingCost(Product product, int count) {
        int price = product.getPrice();
        int productSum = count * price;
        int shippingCost = shippingCalculator.findShippingCostFor(product, count);
        return productSum + shippingCost;
    }

    public int getProductCount() {
        return contents.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    protected int countProductPrice(Product product, Integer count) {
        int price = product.getPrice();
        int productSum = count * price;
        return productSum;
    }

}
