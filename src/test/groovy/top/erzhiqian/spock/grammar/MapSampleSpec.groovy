package top.erzhiqian.spock.grammar

import spock.lang.Specification

class MapSampleSpec extends Specification {

    def "insert value to map use groovy"() {
        given: " a empty map"
        Map<String,String> personRoles = [:]

        when: "insert a value to map"
        personRoles["Stephen Franklin"] = "Doctor"

        then: "the value should exists "
        personRoles["Stephen Franklin"] ==  "Doctor"
    }

    def "replace element in map use groovy"() {
        given: " a  map"
        Map<String,String> personRoles = ["Suzan Ivanova":"Lt.Commander"]

        when: "replace an element value"
        personRoles["Suzan Ivanova"] = "Commander"

        then: "the value should be replaced "
        personRoles["Suzan Ivanova"] ==  "Commander"
    }






}
