package top.erzhiqian.spock.sample.employee;

public class Employee {
    private Integer age;
    private String firstName;
    private String lastName;
    private String middleName;
    private Boolean inTraining;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setInTraining(Boolean inTraining) {
        this.inTraining = inTraining;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", inTraining=" + inTraining +
                '}';
    }
}
