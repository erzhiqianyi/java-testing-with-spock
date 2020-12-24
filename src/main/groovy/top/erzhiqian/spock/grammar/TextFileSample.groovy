package top.erzhiqian.spock.grammar

class TextFileSample {
    static void main(String[] args) {
        String testInput = new File("src/test/resources/quotes.txt").text
        println testInput
    }
}
