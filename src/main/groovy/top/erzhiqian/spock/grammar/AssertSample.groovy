package top.erzhiqian.spock.grammar

import top.erzhiqian.spock.sample.Person

import java.util.regex.Pattern

class AssertSample {
    static void main(String[] args) {
        assert true
        assert !false
        assert true || false
        assert true && !false

        String firstName = "Susan"
        assert firstName

        def lastName = "Ivanova"
        assert lastName

        String empty = ""
        assert !empty

        Person person = new Person()
        assert person

        Person nullReference = null
        assert !nullReference

        int answerToEveryThing = 42
        assert answerToEveryThing

        int zero = 0
        assert !zero

        Object[] array = new Object[3]
        assert array

        Object[] emptyArray = new Object[0]
        assert  !emptyArray

        Pattern regex = ~/needle/
        assert regex.matcher("needle in haystack")
        assert !regex.matcher("wrong haystack")



    }
}
