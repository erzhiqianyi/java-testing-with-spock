package top.erzhiqian.spock.grammar

import spock.lang.Specification
import spock.lang.Unroll
import top.erzhiqian.spock.sample.math.Adder


class UnrollSpec extends Specification {

    def setup() {
        println "Setup prepares next run"
    }

    def "Trivial adder test with failure"() {
        given: "an adder"
        Adder adder = new Adder()
        println "Given: block runs"

        when: "the add method is called for two numbers"
        int result = adder.add(first, second)
        println "When: block runs for first = $first and second = $second"

        then: "the result should be the sum of them"
        result == sum
        println "Then: block is evaluated for sum = $sum"

        where: "some scenarios are"
        first | second || sum
        1     | 1      || 2
        3     | 2      || 7
        3     | -3     || 0
    }


    @Unroll("Adder test #first, #second and #sum (alt2)")
    def "Trivial adder test with failure unroll"() {
        given: "an adder"
        Adder adder = new Adder()
        println "Given: block runs"

        when: "the add method is called for two numbers"
        int result = adder.add(first, second)
        println "When: block runs for first = $first and second = $second"

        then: "the result should be the sum of them"
        result == sum
        println "Then: block is evaluated for sum = $sum"

        where: "some scenarios are"
        first | second || sum
        1     | 1      || 2
        3     | 2      || 7
        3     | -3     || 0
    }


    def cleanup() {
        println "Cleanup releases resources of last run\n"
    }
}