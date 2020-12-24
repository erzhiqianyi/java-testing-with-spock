package top.erzhiqian.spock.sample.employee;

public class SimpleDepartment {
    private String name;
    private String location;


    @Override
    public String toString() {
        return "SimpleDepartment{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
