package top.erzhiqian.spock.grammar

class ExpandSample {
    static void main(String[] args) {
        Expando startIterator = new Expando()
        startIterator.counter = 0
        startIterator.limit = 4
        startIterator.hasNext = {return  counter < limit }
        startIterator.next = {return  counter++}
        startIterator.restartFrom = {from -> counter = from}
        for (Integer number : startIterator as Iterator<Integer>){
            println "Next number is $number"
        }

        println "Reset smart iterator "
        startIterator.restartFrom(2)
        for (Integer number : startIterator as Iterator<Integer>){
            println "Next number is $number"
        }


    }
}
