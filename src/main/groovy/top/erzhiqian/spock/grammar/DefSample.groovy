package top.erzhiqian.spock.grammar

class DefSample {

    static void main(String[] args) {
        String firstName = "Susan"
        def lastName = "Ivanova"
        def fullName = "$firstName $lastName"
        println "def class"
        println fullName
        println "def method"
        println createName(firstName, lastName)
        println "def arguments"
        println createMilitaryName(firstName, lastName, "Commander")
    }

    static def createName(String firstName, String lastName) {
        return "$firstName $lastName"
    }

    static def createMilitaryName(def firstName, def lastName, def rank) {
        return "$firstName $lastName ($rank)"
    }
}
