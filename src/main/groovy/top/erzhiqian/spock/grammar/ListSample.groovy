package top.erzhiqian.spock.grammar

import top.erzhiqian.spock.sample.employee.Employee

class ListSample {
    static void main(String[] args) {
        String[] raceArray = ["Drazi", "Minbari", "Humans"]
        println raceArray


        Employee person1 = new Employee(firstName: "Alice",lastName: "Olson",age: 30)
        Employee person2 = new Employee(firstName: "Jones",lastName: "Corwin",age: 45)
        Employee[] people = [person1,person2]
        println people


    }
}
