package top.erzhiqian.sample

import spock.lang.Specification

class PersonSpec extends Specification {
    def "Testing getters and setters"(){
        when: " a person has both first name and last name"
        SimplePerson person = new SimplePerson()
        person.firstName = "Susan"
        person.lastName = "Ivanova"

        then: "its title should be surname ,name"
        person.createTitle() == "Ivanova,Susan"

    }
}

