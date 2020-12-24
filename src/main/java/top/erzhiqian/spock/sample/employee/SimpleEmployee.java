package top.erzhiqian.spock.sample.employee;

public class SimpleEmployee {
    private String fullName;
    private Integer age;
    private SimpleDepartment department;

    @Override
    public String toString() {
        return "SimpleEmployee{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", department=" + department +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public SimpleDepartment getDepartment() {
        return department;
    }
}
