package top.erzhiqian.spock.sample.eshop

import javafx.collections.ObservableArray
import spock.lang.Specification


class MockAndStubSampleSpec extends Specification {

    def "card has no founds"() {
        given: " a basket , a customer and some products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")
        ChargedBillableBasket basket = new ChargedBillableBasket();

        and: " a credit card service"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)

        and: "a fully stocked warehouse"
        WarehouseInventory inventory = Stub(WarehouseInventory) {
            isProductAvailable(_, _) >> true
            isEmpty() >> false
        }
        basket.setWarehouseInventory(inventory)

        when: "user checks out two products"
        basket.addProduct tv
        basket.addProduct camera
        boolean charged = basket.fullCheckout(customer)

        then: "nothing is charged if credit card does not have enough money "
        1 * creditCardProcessor.authorize(1550, customer) >> CreditCardResult.NOT_ENOUGH_FUNDS
        !charged
        0 * _

    }

    def "happy path for credit card sale"(){
        given: " a basket , a customer and some products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Customer customer = new Customer(name: "John", vip: false, creditCard: "testCard")
        ChargedBillableBasket basket = new ChargedBillableBasket();

        and: "a credit card that has enough funds"
        ICreditCardProcessor creditCardProcessor = Mock(ICreditCardProcessor)
        basket.setCreditCardProcessor(creditCardProcessor)
        CreditCardResult sampleResult = CreditCardResult.OK
        sampleResult.setToken("sample")

        and: "a warehouse"
        WarehouseInventory inventory = Mock(WarehouseInventory)
        basket.setWarehouseInventory(inventory)

        when: "user checks out two prodcuts"
        basket.addProduct tv
        basket.addProduct camera
        boolean  charged = basket.fullCheckout(customer)

        then: "credit card is checked"
        1 * creditCardProcessor.authorize(1500,customer) >> sampleResult

        then: "inventory is checked"
        with(inventory){
            2 * isProductAvailable(!null,1) >> true
            _ * isEmpty() >> false
        }

        then: "credit card is charged"
        1 * creditCardProcessor.capture({myToken -> myToken.endsWith("sample")},customer) >> CreditCardResult.OK
        charged
        0 * _

    }

    
   
}