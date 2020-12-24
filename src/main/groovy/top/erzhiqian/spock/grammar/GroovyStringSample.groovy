package top.erzhiqian.spock.grammar

import top.erzhiqian.spock.sample.employee.SimpleDepartment
import top.erzhiqian.spock.sample.employee.SimpleEmployee

class GroovyStringSample {
    static void main(String[] args) {
        SimpleDepartment sales = new SimpleDepartment(name: "Sales", location: "block c")
        SimpleEmployee employee = new SimpleEmployee(fullName: "Andrew Collins", age: 37, department: sales)
        println "Age is " + employee.getAge()
        println "Age is $employee.age"

        println "Department Location is at " + employee.getDepartment().getLocation()
        println "Department Location is at $employee.department.location"

        println "Person is adult ${employee.age > 18}"
        println "Amount in dollars is \$300"
        println 'Person is adult ${employee.age > 18}'



    }
}
