package top.erzhiqian.spock.sample.math;

public class BugMultiplier {
    public int multiply(int a, int b) {
        if (a == 4) {
            return 5 * b;
        }
        return a * b;
    }
}
