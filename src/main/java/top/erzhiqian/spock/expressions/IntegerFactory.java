package top.erzhiqian.spock.expressions;

public class IntegerFactory {

    public static int createFrom(String number) {
        return MyInteger.valueOf(number.toUpperCase()).getNumber();
    }

}
