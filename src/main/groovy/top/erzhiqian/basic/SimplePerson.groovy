package top.erzhiqian.basic

class SimplePerson {

    String firstName

    String lastName

    String createTitle(){
        return "$lastName,$firstName"
    }
}