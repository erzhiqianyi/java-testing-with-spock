package top.erzhiqian.spock.sample.eshop

import spock.lang.Shared
import spock.lang.Specification

class ShareSpec extends Specification {

    @Shared
    CreditCardProcessor creditCardProcessor

    BillableBasket basket

    def setupSpec() {
        creditCardProcessor = new CreditCardProcessor()
    }

    def setup() {
        basket = new BillableBasket()
        creditCardProcessor.newStayStarted()
        basket.setCreditCardProcessor(creditCardProcessor)
    }

    def "user buys a single product"() {
        given: " an empty basket and a tv."
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)

        and: " user wants to buy the tv"
        basket.addProduct(tv)

        when: "user checks out"
        basket.checkout()

        then: "revenue is the same as the price of the TV"
        creditCardProcessor.currentRevenue == tv.price

    }


    def "user buys a two products"() {
        given: " an empty basket and a camera."
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)

        and: " user wants to two cameras"
        basket.addProduct(camera, 2)

        when: "user checks out"
        basket.checkout()

        then: "revenue is the same as the price of both products"
        creditCardProcessor.currentRevenue == 2 * camera.price

    }

    def cleanup() {
        basket.clearAllProducts()
    }

    def cleanupSpec() {
        creditCardProcessor.shutDown()
    }


}