package top.erzhiqian.spock.grammar

import spock.lang.Specification


class LifecycleSpec extends Specification {
    def setupSpec() {
        println "will run only once."
    }

    def setup() {
        println "will run  before each feature"
    }

    def "first feature being tested"() {
        expect: "trivial test"
        println "first feature runs"
        2 == 1 + 1
    }

    def "second feature being tested"() {
        expect: "trivial test"
        println "second feature runs"
        5 == 3 + 2
    }

    def cleanup() {
        println "will run after each feature"
    }

    def cleanupSpec(){
        println "will run only once at the end ."
    }

}