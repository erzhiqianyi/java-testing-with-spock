package top.erzhiqian.spock.sample;

import org.junit.Test;
import top.erzhiqian.spock.sample.employee.Department;
import top.erzhiqian.spock.sample.employee.Employee;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void assign() {
        Employee trainee = new Employee();
        trainee.setAge(22);
        trainee.setFirstName("Alice");
        trainee.setLastName("Olson");
        trainee.setInTraining(true);

        Employee seasoned = new Employee();
        seasoned.setAge(45);
        seasoned.setFirstName("Ajex");
        seasoned.setMiddleName("Jones");
        seasoned.setLastName("Corwin");
        List<Employee> people = Arrays.asList(trainee, seasoned);
        Department department = new Department();
        department.assign(people);
        assertNotNull(department.getEmployees());
    }
}