package top.erzhiqian.spock.sample

class SimplePerson {

    String firstName

    String lastName

    String createTitle(){
        return "$lastName,$firstName"
    }
}