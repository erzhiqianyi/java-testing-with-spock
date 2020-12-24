package top.erzhiqian.spock.sample

class PersonGetterAndSetter {

    public static void main(String[] args) {
        def person = new Person()
        person.setFirstName("Lyta")
        person.setLastName("Alexander")
        System.out.println("Person first name is " + person.getFirstName())
        System.out.println("Person last name is " + person.getLastName())
    }

}