package top.erzhiqian.spock.grammar

import spock.lang.Specification

class ListSampleSpec extends Specification {

    def "using index access list element"() {
        when: "a list of human ships"
        List<String> humanShips = ["Condor", "Explorer"]

        then:
        humanShips.get(0) == "Condor"
        humanShips[0] == "Condor"
    }

    def "replacing an element in list"() {
        given: " a list of human ships "
        List<String> humanShips = ["Condor", "Explorer"]

        when: "replace an element"
        humanShips[3] = "Omega"

        then: "element should be replaced "
        humanShips[3] == "Omega"
    }


}
