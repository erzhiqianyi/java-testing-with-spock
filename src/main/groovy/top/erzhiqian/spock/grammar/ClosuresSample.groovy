package top.erzhiqian.spock.grammar

class ClosuresSample {
    static void main(String[] args) {
        Closure simpleClosure = {int x  -> return x * 2}
        assert simpleClosure(3) == 6

        def simple =  { x -> x * 2}
        assert  simple(3) == 6

        def twoArguments = { x, y -> x + y}
        assert  twoArguments(3,5) == 8

    }
}
