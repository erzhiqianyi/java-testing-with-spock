package top.erzhiqian.spock.sample.employee;

import java.util.List;

public class Department {
    private List<Employee> employees;

    public void assign(List<Employee> employees) {
        setEmployees(employees);
    }

    private void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "employees=" + employees +
                '}';
    }
}
