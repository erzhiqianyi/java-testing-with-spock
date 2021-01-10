package top.erzhiqian.spock.sample.eshop;

import java.util.Map;

public class EnterprisyBasket extends Basket {

    private WarehouseInventory warehouseInventory = null;

    private String language;

    private boolean autoRefresh;

    private int numberOfCaches;

    public void enableAutoRefresh() {
        this.autoRefresh = true;
    }

    public void setNumberOfCaches(int number) {
        this.numberOfCaches = number;
    }

    public void setCustomerResolver(DefaultCustomerResolver defaultCustomerResolver) {

    }

    @Override
    public void setWarehouseInventory(WarehouseInventory warehouseInventory) {
        this.warehouseInventory = warehouseInventory;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void checkout() {
        for (Map.Entry<Product, Integer> entry : contents.entrySet()) {
            warehouseInventory.subtract(entry.getKey().getName(), entry.getValue());
        }
    }


}
