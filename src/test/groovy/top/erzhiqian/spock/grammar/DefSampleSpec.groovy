package top.erzhiqian.spock.grammar

import spock.lang.Specification

class DefSampleSpec extends Specification {

    public void trivialSumJava() {
        when: "number is one"
        int number = 1

        then: "number plus number is two"
        number + number == 2
    }

    def trivialSumDef(){
        when: "number is one "
        int number = 1

        then: "number plus number is two"
        number + number == 2
    }

    def "Testing a trivial sum"(){
        when: "number is one "
        int number = 1

        then: "number plus number is two"
        number + number == 2

    }
}
