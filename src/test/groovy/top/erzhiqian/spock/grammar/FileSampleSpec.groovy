package top.erzhiqian.spock.grammar

import groovy.json.JsonSlurper
import spock.lang.Specification
import top.erzhiqian.spock.sample.file.WordDetector

class FileSampleSpec extends Specification {

    def "reading a text file"() {
        given: " a text file path  and a WordDetector"
        String filePath = "src/test/resources/quotes.txt"
        WordDetector wordDetector = new WordDetector()

        when: "read the file and process it"
        String inputText = new File(filePath).text
        wordDetector.parseText(inputText)

        then: "word count should be correct"
        wordDetector.wordsFound() == 78
    }

    def "reading a text file line by line "() {
        given: " a text file path  and a WordDetector"
        String filePath = "src/test/resources/quotes.txt"
        WordDetector wordDetector = new WordDetector()

        when: "read the file line by line  "
        List<String> inputText = new File(filePath).readLines()
        for (String line : inputText) {
            wordDetector.feedText(line)
        }

        then: "word count should be correct"
        wordDetector.wordsFound() == 78
    }


    def "reading a xml file "() {
        given: " a xml file path "
        String filePath = "src/test/resources/employee-data.xml"

        when: "read the file   "
        def xmlRoot = new XmlSlurper().parse(filePath)

        then: "file should be read"
        xmlRoot.department.size() == 1
        xmlRoot.department.@name == "sales"
        xmlRoot.department.employee[0].firstName == "Orlando"
        xmlRoot.department.employee[0].lastName == "Boren"
        xmlRoot.department.employee[0].age == 24
        xmlRoot.department.employee[1].firstName == "Diana"
        xmlRoot.department.employee[1].lastName == "Colgan"
        xmlRoot.department.employee[1].age == 28
    }


    def "reading a json file "() {
        given: " a json file path "
        String filePath = "src/test/resources/employee-data.json"

        when: "read the file   "
        def jsonRoot = new JsonSlurper().parse(filePath)

        then: "file should be read"
        jsonRoot.staff.department.size() == 1
        jsonRoot.staff.department.name == "sales"
        jsonRoot.staff.department.employee[0].firstName == "Orlando"
        jsonRoot.staff.department.employee[0].lastName == "Boren"
        jsonRoot.staff.department.employee[0].age == 24
        jsonRoot.staff.department.employee[1].firstName == "Diana"
        jsonRoot.staff.department.employee[1].lastName == "Colgan"
        jsonRoot.staff.department.employee[1].age == 28
    }


}
