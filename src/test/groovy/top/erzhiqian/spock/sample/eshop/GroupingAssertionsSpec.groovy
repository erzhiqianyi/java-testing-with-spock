package top.erzhiqian.spock.sample.eshop

import spock.lang.Specification


class GroupingAssertionsSpec extends Specification {
    def "buying products reduces the inventory availability"() {
        given: "an inventory with product"
        Product laptop = new Product(name: "toshiba", price: 1200, weight: 5)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        WarehouseInventory inventory = new WarehouseInventory()
        inventory.preload(laptop, 3)
        inventory.preload(camera, 5)
        inventory.preload(hifi, 2)

        and: "an empty basket"
        EnterprisyBasket basket = new EnterprisyBasket()
        basket.setWarehouseInventory(inventory)
        basket.setCustomerResolver(new DefaultCustomerResolver())
        basket.setLanguage("english")
        basket.setNumberOfCaches(3)
        basket.enableAutoRefresh()

        when: "user gets a laptop and two camera"
        basket.addProduct(camera, 2)
        basket.addProduct(laptop)

        and: "user completes the transaction"
        basket.checkout()

        then: "warehouse is updated accordingly"
        !inventory.isEmpty()
        inventory.getBoxesMovedToday() == 3
        inventory.availableOfProduct("toshiba") == 2
        inventory.availableOfProduct("panasonic") == 3
        inventory.availableOfProduct("jvc") == 2

    }

    def "buying products reduces the inventory availability (alt)"() {
        given: "an inventory with product"
        Product laptop = new Product(name: "toshiba", price: 1200, weight: 5)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        WarehouseInventory inventory = new WarehouseInventory()
        inventory.with {
            preload laptop, 3
            preload camera, 5
            preload hifi, 2
        }

        and: "an empty basket"
        EnterprisyBasket basket = new EnterprisyBasket()
        basket.with {
            setWarehouseInventory(inventory)
            setCustomerResolver(new DefaultCustomerResolver())
            setLanguage("english")
            setNumberOfCaches(3)
            enableAutoRefresh()
        }

        when: "user gets a laptop and two camera"
        basket.with {
            addProduct camera, 2
            addProduct laptop
        }

        and: "user completes the transaction"
        basket.checkout()

        then: "warehouse is updated accordingly"
        with(inventory) {
            !isEmpty()
            getBoxesMovedToday() == 3
            availableOfProduct("toshiba") == 2
            availableOfProduct("panasonic") == 3
            availableOfProduct("jvc") == 2
        }
    }

}