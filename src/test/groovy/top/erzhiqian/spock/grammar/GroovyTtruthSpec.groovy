package top.erzhiqian.spock.grammar

import spock.lang.Specification
import top.erzhiqian.spock.sample.file.WordDetector


class GroovyTtruthSpec extends Specification {
    def "demo from Groovy truth"() {
        when: " a line of text is process"
        WordDetector wordDetector = new WordDetector()
        wordDetector.parseText("Understanding is a three edged sword: your side, their side, and the truth")

        then: "word frequency should be correct"
        wordDetector.wordsFound() > 0
        wordDetector.duplicatesFound().size() > 0

        wordDetector.wordsFound()
        wordDetector.duplicatesFound()
    }

}