package top.erzhiqian.spock.sample

class PersonScript {
    static void main(String[] args) {
        Person person = new Person()
        person.firstName = "Susan"
        person.lastName = "Ivanova"
        person.rank = "Commander"
        println "Person first name is $person.firstName"
        println "Person last name is $person.lastName"
        println "Person rank is $person.rank"
    }
}