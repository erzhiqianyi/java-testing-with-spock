package top.erzhiqian.spock.sample.eshop;

public class ServiceLocatorBasket  extends Basket{
    public ServiceLocatorBasket(ServiceLocator serviceLocator) {
        setWarehouseInventory(serviceLocator.getWarehouseInventory());
    }
}
