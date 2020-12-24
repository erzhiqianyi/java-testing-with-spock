package top.erzhiqian.spock.grammar

import spock.lang.Specification
import top.erzhiqian.spock.sample.file.FileExtensionFilter

class ClosuresSampleSpec extends Specification {

    def "Testing Jpeg files"() {
        given: "a list of filenames and a  FileExtensionFilter"
        List<String> testInput = ["image1.jpg","image2.jpg","image3.jpeg","image4.gif","image5.jpg","image6.tiff"]
        FileExtensionFilter myFilter = new FileExtensionFilter()

        when: "FileExtensionFilter add  valid  extension"
        myFilter.addValidExtension("jpeg")
        myFilter.addValidExtension("jpg")

        and: "filer filenames"
        List<String> result = myFilter.filterFileNames(testInput)

        then: "result should not contain other types "
        result.every({ filename -> filename.endsWith("jpeg") || filename.endsWith("jpg") })
    }


}
