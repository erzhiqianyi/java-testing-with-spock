package top.erzhiqian.spock.grammar

import spock.lang.Specification
import top.erzhiqian.spock.expressions.IntegerFactory
import top.erzhiqian.spock.expressions.MyInteger
import top.erzhiqian.spock.sample.math.Adder

class ExpressionsSampleSpec extends Specification {

    def "Testing the Adder for #fisrt + #second = #sum"() {
        given: " an adder"
        Adder adder = new Adder()

        expect: "that it calculates the sum of two numbers"
        adder.add(first, second) == sum

        where: "some scenarios are"
        first                            | second                     || sum
        2 + 3                            | 10 - 2                     || new Integer(13).value
        MyInteger.FIVE.getNumber()       | MyInteger.NINE.getNumber() || 14
        IntegerFactory.createFrom("two") | (7 - 2) * 2                || 12
        [1, 2, 3].get(1)                 | 3                          || IntegerFactory.createFrom("five")
        new Integer(5).intValue()        | new String("cat").size()   || MyInteger.EIGHT.getNumber()
        ["hello", "world"].size()        | 5                          || IntegerFactory.createFrom("seven")

    }

}