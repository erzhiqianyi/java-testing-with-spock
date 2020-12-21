package top.erzhiqian.basic

import spock.lang.Specification

class DefSpec extends Specification {

    void trivialSumOne() {
        when: "number is one "
        int number = 1

        then: "number plus number is two"
        number + number == 2
    }

    def trivialSumTwo() {
        when: "number is one "
        int number = 1

        then: "number plus number is two"
        number + number == 2

    }

    def "Testing a trivial sum"() {
        when: "number is one "
        def number = 1

        then: "number plus number is two"
        number + number == 2

    }
}

