package top.erzhiqian.spock.sample.eshop

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll


@Title("Unit test for basket ")
@Narrative(""" A empty basket starts with no
weight. Adding products to the basket
increases its weight. The weight is
then used for billing during shipping calculations.
Electronic goods have always zero weight.
""")
@Subject(Basket)
@Unroll
class StubSampleSpec extends Specification {

    def "if warehouse is empty nothing can be shipped"() {
        given: " a basket and TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()


        and: " an empty warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isEmpty() >> true
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely()

    }

    def "if  warehouse has the product on stock everything is fine"() {
        given: " a basket and TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: " a warehouse with enough stock"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", 1) >> true
        inventory.isEmpty() >> false
        basket.setWarehouseInventory(inventory)


        when: " user checks out the tv"
        basket.addProduct tv

        then: "order can be shipped right away"
        basket.canShipCompletely()

    }

    def "If warehouse does not have all products,order cannot be shipped"() {
        given: " a basket and a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: " a warehouse with partial availability"
        WarehouseInventory inventory = Stub(WarehouseInventory) {
            isProductAvailable("bravia", 1) >> true
            isProductAvailable("panasonic", 1) >> false
            isEmpty() >> false
        }
        basket.warehouseInventory = inventory


        when: "user  checks out both products"
        basket.addProduct tv
        basket.addProduct camera

        then: "order cannot be shipped right away"
        !basket.canShipCompletely()

    }

    def "If warehouse has both products everything is fine"() {
        given: " a basket and a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with enough stock"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_, 1) >> true
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tvv and the camera"
        basket.addProduct tv
        basket.addProduct camera

        then: "order can be shipped right away"
        basket.canShipCompletely()

    }

    def "If warehouse if fully stocked stock everything is fine"() {
        given: " a basket and a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_, _) >> true
        basket.setWarehouseInventory(inventory)

        when: "user checks out multiple products"
        basket.addProduct tv, 33
        basket.addProduct camera, 12

        then: "order can be shipped right away"
        basket.canShipCompletely()

    }

    def "Inventory is always checked in the last possible moment"() {
        given: " a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with fluctuating stock levels"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", _) >>> true >> false
        inventory.isEmpty() >>> [false, true]
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order can be shipped right away"
        basket.canShipCompletely()

        when: "user wants another TV"
        basket.addProduct tv

        then: "order can no longer be shipped"
        !basket.canShipCompletely()

    }

    @Unroll
    def "A problematic inventory means nothing can be shipped"() {
        given: " a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "a warehouse with serious issues"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable("bravia", _) >> { throw new RuntimeException("critical error") }
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely()

    }

    def "Basket handles shipping charges according to product count"() {
        given: "a basket and several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasoinc", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        Product laptop = new Product(name: "toshiba", price: 800, weight: 10)
        Basket basket = new Basket()

        and: "a fully stock warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_, _) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator)
        shippingCalculator.findShippingCostFor(_, _) >> { Product product, int count -> 10 * count }
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out several products in different quantities"
        basket.addProduct tv, 2
        basket.addProduct camera, 2
        basket.addProduct hifi
        basket.addProduct laptop, 3

        then: "cost is correctly calculated"
        basket.findTotalCost() == 2 * tv.price + 2 * camera.price + hifi.price + 3 * laptop.price + basket.getProductCount() * 10

    }

    def "Downloadable goods do not have shipping cost"() {
        given: "a basket and several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)
        Product laptop = new Product(name: "toshiba", price: 800, weight: 10)
        Product ebook = new Product(name: "learning exposure", price: 30, weight: 0)
        Product suite = new Product(name: "adobe essentials", price: 200, weight: 0)
        Basket basket = new Basket()

        and: "a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isProductAvailable(_, _) >> true
        basket.setWarehouseInventory(inventory)

        and: "a shipping calculator that charges 10 dollars for each physical product"
        ShippingCalculator shippingCalculator = Stub(ShippingCalculator)
        shippingCalculator.findShippingCostFor(_, _) >>
                { Product product, int count -> product.weight == 0 ? 0 : 10 * count }
        basket.setShippingCalculator(shippingCalculator)


        when: "user checks out several products in different quantities"
        basket.addProduct tv, 2
        basket.addProduct camera, 2
        basket.addProduct hifi
        basket.addProduct laptop
        basket.addProduct ebook
        basket.addProduct suite, 3

        then: "cost is correctly calculated"
        basket.findTotalCost() == 2 * tv.price +
                2 * camera.price +
                hifi.price +
                laptop.price +
                ebook.price +
                3 * suite.price +
                60

    }

    def "If warehouse is empty nothing can be shipped "() {
        given: " a TV"
        Product tv = new Product(name: "bravia",price: 1200,weight: 18)

        and: "an empty warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory)
        inventory.isEmpty() >> true
        ServiceLocator  serviceLocator = Stub(ServiceLocator)
        serviceLocator.getWarehouseInventory() >> inventory

        and: "a basket"
        ServiceLocatorBasket basket = new ServiceLocatorBasket(serviceLocator)

        when: "user checks out the tv"
        basket.addProduct tv

        then:"order cannot be shipped"
        !basket.canShipCompletely()
    }
}