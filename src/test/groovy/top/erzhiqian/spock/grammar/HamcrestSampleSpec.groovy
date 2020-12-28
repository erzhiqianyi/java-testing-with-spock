package top.erzhiqian.spock.grammar

import spock.lang.Specification
import static org.hamcrest.CoreMatchers.not
import static org.hamcrest.CoreMatchers.hasItem
import static spock.util.matcher.HamcrestSupport.expect
import static spock.util.matcher.HamcrestSupport.that

class HamcrestSampleSpec extends Specification {
    def "trivial test with Hamcrest"() {
        given: "a list of products"
        List<String> products = ["Camera", "laptop", "hifi"]

        expect: "camera should be one of them"
        products hasItem("Camera")

        and: "hotdog is not one of them"
        products not(hasItem("hotdog"))

    }


    def "trivial test with Hamcrest (alt)"() {
        given: "an empty list "
        List<String> products = new ArrayList<>()

        when: "is is filled with products"
        products.add("Camera")
        products.add("laptop")
        products.add("hifi")

        then: "camera should be one of them"
        expect(products, hasItem("Camera"))

        and: "hotdog is not one of them"
        that(products, not(hasItem("hotdog")))

    }

    def "trivial test with Groovy closure"(){
        given: "a list of products"
        List<String> products = ["Camera","laptop","hifi"]

        expect: "Camera should be one of them"
        products.any{productName -> productName == "Camera"}

        and: "hotdog is not one of them"
        products.every{productName -> productName != "hotdog"}
    }

}