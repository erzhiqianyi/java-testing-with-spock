package top.erzhiqian.spock.sample.eshop


import spock.lang.Specification

class MockSampleSpec extends Specification {
    def "If warehouse is empty nothing can be shipped"() {
        given: "a basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        and: "an empty warehouse"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        inventory.isEmpty() >> true
        basket.setWarehouseInventory(inventory)

        when: "user checks out the tv"
        basket.addProduct tv

        then: "order cannot be shipped"
        !basket.canShipCompletely()
    }

    def "credit card connection is always closed down"() {
        given: "a basket ,a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        ChargedBillableBasket basket = new ChargedBillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out the tv"
        basket.addProduct tv
        basket.checkout(customer)

        then: "connection is always closed at the end"
        1 * creditCardProcessor.shutdown()


    }

    def "credit card connection is closed down in the end"() {
        given: "a basket , a customer and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        ChargedBillableBasket basket = new ChargedBillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out the tv"
        basket.addProduct tv
        basket.checkout(customer)

        then: "credit card is charged and "
        1 * creditCardProcessor.sale(_, _)

        then: "the credit card service is closed down"
        1 * creditCardProcessor.shutdown()
    }

    def "Warehouse is queried for each product"() {
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can be shipped"
        readyToShip
        2 * inventory.isProductAvailable(_, _) >> true
        0 * inventory.preload(_, _)

    }

    def "Warehouse is queried for each product - strict"() {
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can  be shipped"
        readyToShip
        2 * inventory.isProductAvailable(_, _) >> true
        1 * inventory.isEmpty() >> false
        0 * inventory._

    }

    def "Only warehouse is queried when checking shipping status"() {
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)
        ShippingCalculator shippingCalculator = Mock(ShippingCalculator)
        basket.setShippingCalculator(shippingCalculator)

        when: "user checks out  both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can be shipped"
        readyToShip
        2 * inventory.isProductAvailable(_, _) >> true
        _ * inventory.isEmpty() >> false
        0 * _
    }

    def "Warehouse is queried for each product - null"() {
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: "a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "user checks out both product"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can be shipped"
        readyToShip
        2 * inventory.isProductAvailable(!null, 1) >> true
    }

    def "Warehouse is queried for each product - type"() {
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        and: " a warehouse with limitless stock"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "user checks out both products"
        basket.addProduct tv
        basket.addProduct camera
        boolean readyToShip = basket.canShipCompletely()

        then: "order can be shipped"
        readyToShip
        2 * inventory.isProductAvailable(_ as String, _ as Integer) >> true

    }

    def "vip status is correctly passed to credit card - simple"() {
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        ChargedBillableBasket basket = new ChargedBillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: " a credit card service"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        basket.checkout(customer)

        then: "credit card is charged"
        1 * creditCardProcessor.sale(1550,customer)

    }

    def "vip status is correctly passed to credit card - vip" (){
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        ChargedBillableBasket basket = new ChargedBillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        basket.checkout(customer)

        then: "credit card is charged"
        1 * creditCardProcessor.sale(1550 , {client -> client.vip == false})

    }

    def "vip status is correctly passed to credit card - full"(){
        given: "a basket , a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        ChargedBillableBasket basket = new ChargedBillableBasket()
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")

        and: "a credit card service"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        basket.checkout(customer)

        then: "credit card is charged"
        1 * creditCardProcessor.sale({amount -> amount == basket.findOrderPrice()},{client -> client.vip == false})

    }
}