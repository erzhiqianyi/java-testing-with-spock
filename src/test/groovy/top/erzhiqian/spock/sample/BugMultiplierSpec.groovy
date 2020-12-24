package top.erzhiqian.spock.sample

import spock.lang.Specification
import top.erzhiqian.spock.sample.math.Adder
import top.erzhiqian.spock.sample.math.BugMultiplier

class BugMultiplierSpec extends Specification {

    def "Multiply two numbers and return the result "() {
        when: "a new bug multiplier class is created"
        def multiplier = new BugMultiplier()

        then: " 3 times 7 is 21"
        multiplier.multiply(3, 7) == 21
    }

    def "Combine both multiplication and addition"() {
        when: " a new bug multiplier  and  an Adder are created "
        def multiplier = new BugMultiplier()
        def adder = new Adder()

        then: "4 times (2 plus 3) is 20"
        multiplier.multiply(4, adder.add(2, 3)) == 20

        and: "(2 plus 3) times 4 is also 20"
        multiplier.multiply(adder.add(2, 3), 4) == 20
    }

}
