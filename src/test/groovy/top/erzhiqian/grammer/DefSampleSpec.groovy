package top.erzhiqian.grammer

import spock.lang.Specification

class DefSampleSpec extends Specification {

    public void trivialSumJava() {
        when: "number is one"
        int number = 1

        then: "number plus numnber is two"
        number + number == 2
    }
}
