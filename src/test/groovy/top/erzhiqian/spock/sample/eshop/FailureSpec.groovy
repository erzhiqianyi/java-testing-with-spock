package top.erzhiqian.spock.sample.eshop

import spock.lang.Specification


class FailureSpec extends Specification {
    def "Adding products to a basket increases its weight"() {
        given: " en empty basket"
        ProblematicBasket basket = new ProblematicBasket()

        and: "two different products"
        Product laptop = new Product(name: "toshiba", price: 1200, weight: 5)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)

        when: "user gets a laptop and two cameras"
        basket.addProduct(camera, 2)
        basket.addProduct(laptop)

        then: "basket weight is updated accordingly"
        basket.currentWeight == (2 * camera.weight) + laptop.weight
    }

}