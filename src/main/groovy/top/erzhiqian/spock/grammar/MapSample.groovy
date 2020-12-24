package top.erzhiqian.spock.grammar

import top.erzhiqian.spock.sample.employee.Address
import top.erzhiqian.spock.sample.employee.Employee

class MapSample {
    static void main(String[] args) {
        Map<String, Integer> wordCounts = ["Hello": 1, "groovy": 1, "World": 2]
        println wordCounts

        Employee person1 = new Employee(firstName: "Alice",lastName: "Olson",age: 30)
        Employee person2 = new Employee(firstName: "Jones",lastName: "Corwin",age: 45)

        Address address1 = new Address(street: "Marley",number: 25)
        Address address2 = new Address(street: "Barnam",number: 7)

        Map<Employee,Address> starrAddress = [(person1):address1,(person2):address2]
        println starrAddress
    }
}
