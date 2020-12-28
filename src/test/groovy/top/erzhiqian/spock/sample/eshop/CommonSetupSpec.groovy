package top.erzhiqian.spock.sample.eshop


import spock.lang.Specification
import spock.lang.Subject

@Subject(Basket)
class CommonSetupSpec extends Specification {


    Product tv
    Product camera

    def setup(){
        tv = new Product(name: "bravia",price: 1200,weight: 18)
        camera = new Product(name: "panasonic",price: 350,weight: 2)
    }

    def "A basket with one product weights as that product"(){
        given: "a empty basket "
        Basket basket = new Basket()

        when: "user wants to buy the tv"
        basket.addProduct(tv)

        then: "basket weight is equals to the TV"
        basket.currentWeight == tv.weight

    }

    def "A basket with two products weights as their sum"(){
        given: "a empty basket "
        Basket basket = new Basket()

        when: "user wants to buy the tv..."
        basket.addProduct tv

        and: "..the camera"
        basket.addProduct camera


        then: "basket weight is equal to all product weight"
        basket.currentWeight == (tv.weight + camera.weight)
    }


}