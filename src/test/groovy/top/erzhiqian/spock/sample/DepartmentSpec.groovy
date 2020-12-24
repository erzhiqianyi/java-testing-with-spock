package top.erzhiqian.spock.sample

import spock.lang.Specification
import top.erzhiqian.spock.sample.employee.Department
import top.erzhiqian.spock.sample.employee.Employee

class DepartmentSpec extends Specification {

    def "assign people to department"() {
        given: "a trainee , a   seasoned employee  and a department "
        Employee trainee = new Employee(age: 22, firstName: "Alice", lastName: "Olson", inTraining: true)
        Employee seasoned = new Employee(middleName: "Jones", lastName: "Corwin", age: 45, firstName: "Alex")
        Department department = new Department()

        when: "assign people to department"
        List<Employee> people = Arrays.asList(trainee, seasoned)
        department.assign(people)

        then: "the department should have people"
        department.getEmployees().size() > 0
    }

    def "assign people to department use simple list grammer"() {
        given: "a trainee , a   seasoned employee  and a department "
        List<Employee> people = [
                new Employee(age: 22, firstName: "Alice", lastName: "Olson", inTraining: true),
                new Employee(middleName: "Jones", lastName: "Corwin", age: 45, firstName: "Alex")
        ]
        Department department = new Department()

        when: "assign people to department"
        department.assign(people)

        then: "the department should have people"
        department.getEmployees().size() > 0
    }
}
