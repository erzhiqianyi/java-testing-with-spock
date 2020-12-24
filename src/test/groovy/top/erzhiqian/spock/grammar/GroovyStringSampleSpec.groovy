package top.erzhiqian.spock.grammar

import spock.lang.Specification
import top.erzhiqian.spock.sample.file.WordDetector

class GroovyStringSampleSpec extends Specification {

    def "spilt a big string with newlines"() {
        given: " a paragraph  and a WordDetector"
        String input = '''I want you to know you were right. 
                I didn't want  to admit that. Just pride I guess. You get my age, 
                you  get kinda set in your ways. 
                It had to be  done. Don't blame yourself for what happened later.'''
        WordDetector wordDetector = new WordDetector()

        when: "the paragraph is processed"
        wordDetector.parseText(input)

        then: "word count should be correct"
        wordDetector.wordsFound()  == 34
    }


}
