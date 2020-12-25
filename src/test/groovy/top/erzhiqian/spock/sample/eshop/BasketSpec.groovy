package top.erzhiqian.spock.sample.eshop

import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Unit test for basket weight")
@Narrative(""" A empty basket starts with no
weight. Adding products to the basket
increases its weight. The weight is
then used for billing during shipping calculations.
Electronic goods have always zero weight.
""")
@Subject(Basket)
class BasketSpec extends Specification {
    def "a basket with one product has equal weight"() {
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the tv"
        basket.addProduct(tv)

        then: "basket weight is equals to the TV"
        basket.getCurrentWeight() == tv.getWeight()
    }

    def "a basket with one product has equal weight (alternative)"() {
        setup: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the tv"
        basket.addProduct(tv)

        then: "basket weight is equals to the TV"
        basket.getCurrentWeight() == tv.getWeight()
    }


    def "a basket with two product weights are their sum"() {
        given: "an empty basket and a TV and a camera"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Basket basket = new Basket()

        when: "user wants to buy the tv and the camera"
        basket.addProduct(tv)
        basket.addProduct(camera)

        then: "basket weight is equals to all products"
        basket.getCurrentWeight() == (tv.getWeight() + camera.getWeight())

    }


    def "a basket with two product weights are their sum (and block in given)"() {
        given: "an empty basket."
        Basket basket = new Basket()

        and: "several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)

        when: "user wants to buy the tv and the camera"
        basket.addProduct tv
        basket.addProduct camera
        basket.addProduct hifi

        then: "basket weight is equals to all products"
        basket.getCurrentWeight() == (tv.weight + camera.weight + hifi.weight)

    }

    def "a basket with two product weights are their sum (and block in when)"() {
        given: "an empty basket."
        Basket basket = new Basket()

        and: "several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)

        when: "user wants to buy the tv.."
        basket.addProduct tv

        and: "..the camera.."
        basket.addProduct camera

        and: "..the"
        basket.addProduct hifi

        then: "basket weight is equals to all products"
        basket.getCurrentWeight() == (tv.weight + camera.weight + hifi.weight)

    }

    def "a basket with two product weights are their sum (and block in then)"() {
        given: "an empty basket."
        Basket basket = new Basket()

        and: "several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)
        Product hifi = new Product(name: "jvc", price: 600, weight: 5)

        when: "user wants to buy the tv.."
        basket.addProduct tv

        and: "..the camera.."
        basket.addProduct camera

        and: "..the"
        basket.addProduct hifi

        then: "basket weight is equals to all products"
        basket.getCurrentWeight() == (tv.weight + camera.weight + hifi.weight)

    }


    def "a basket with one product has equal weight(clean up)"() {
        given: "an empty basket and a TV"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Basket basket = new Basket()

        when: "user wants to buy the tv"
        basket.addProduct(tv)

        then: "basket weight is equals to th TV"
        basket.getCurrentWeight() == tv.getWeight()

        cleanup: "refresh basket  resources"
        basket.clearAllProducts()
    }


    def "a basket with one product has equal weight(better)"() {
        given: "an empty basket"
        @Subject
        Basket basket = new Basket()

        and: "several products"
        Product tv = new Product(name: "bravia", price: 1200, weight: 18)
        Product camera = new Product(name: "panasonic", price: 350, weight: 2)

        when: "user wants to buy the tv.."
        basket.addProduct tv

        and: "..the camera"
        basket.addProduct camera

        then: "basket weight is equals to all product weight"
        basket.getCurrentWeight() == (tv.getWeight() + camera.getWeight())

   }

}
