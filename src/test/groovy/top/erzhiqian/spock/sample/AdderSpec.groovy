package top.erzhiqian.spock.sample

import spock.lang.Specification
import top.erzhiqian.spock.sample.math.Adder

class AdderSpec extends Specification {

    def "Adding two numbers to return the sum"() {
        when: "a new Adder class is created"
        def adder = new Adder()

        then: "1 plus 1 is 2"
        adder.add(1, 1) == 2
    }

    def "Order of numbers does not matter"() {
        when: "a new Adder class is created"
        def adder = new Adder();

        then: "2 plus 3 is 5"
        adder.add(2, 3) == 5

        and: "3 plus 2 is also 5"
        adder.add(3, 2) == 5
    }

    def "Trivial adder test"(){
        given: "an adder"
        Adder adder = new Adder()

        expect: "that it calculates the sum of two numbers"
        adder.add(fisrt,second) == sum

        where: "some scenarios are"
        fisrt | second || sum
        1     | 1      || 2
        3     | 2      || 5
        82    | 16     || 98
        3     | -3     || 0
        0     | 0      || 0
    }

    def "Trivial adder test (alt)"(int first,int second,int sum){
        given: "an adder"
        Adder adder = new Adder()

        expect: "that it calculates the sum of two numbers"
        adder.add(first,second) == sum

        where: "some scenarios are"
        first | second || sum
        1     | 1      || 2
        3     | 2      || 5
        82    | 16     || 98
        3     | -3     || 0
        0     | 0      || 0
    }
}
