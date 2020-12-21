package top.erzhiqian.sample

class SimplePerson {

    String firstName

    String lastName

    String createTitle(){
        return "$lastName,$firstName"
    }
}