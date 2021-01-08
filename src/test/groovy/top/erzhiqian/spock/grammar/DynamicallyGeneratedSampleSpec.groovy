package top.erzhiqian.spock.grammar

import spock.lang.Specification
import spock.lang.Unroll
import top.erzhiqian.spock.expressions.InvalidNamesGen
import top.erzhiqian.spock.expressions.MultiVarReader
import top.erzhiqian.spock.sample.file.ImageNameValidator
import top.erzhiqian.spock.sample.math.Calculator


class DynamicallyGeneratedSampleSpec extends Specification {

    def "Multiplying #first and #second is always a negative number (range)"() {
        given: " a calculator"
        Calculator calculator = new Calculator()

        expect: "that multiplying a positive and negative number is also negative"
        calculator.multiply(first, second) < 0

        where: "some scenarios are"
        first << (20..80)
        second << (-65..-5)
    }


    @Unroll("Checking image name #pictureFile")
    def "All kinds of JPEG file are accepted"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator();

        expect: "that all jpeg filenames are accepted regardless of case"
        validator.isValidImageExtension(pictureFile)

        where: "sample image names are "
        pictureFile << GroovyCollections.combinations([["sample.", "Sample.", "SAMPLE."],
                                                       ['j', "J"], ['p', 'P'], ['e', 'E'], ['g', 'G']])*.join()
    }


    def "Multiplying #first and #second is always a negative number (constant parameters)"() {
        given: " a calculator"
        Calculator calculator = new Calculator()

        expect: "that multiplying a positive and negative number is also negative"
        calculator.multiply(first, second) < 0

        where: "some scenarios are"
        first << (20..80)
        second = -1
    }

    def "Multiplying #first and #second is always a negative number (derived parameters)"() {
        given: " a calculator"
        Calculator calculator = new Calculator()

        expect: "that multiplying a positive and negative number is also negative"
        calculator.multiply(first, second) < 0

        where: "some scenarios are"
        first << (20..80)
        second = first * -1
    }


    @Unroll("Checking image name #pictureFile")
    def " Valid images are PNG and JPEG file (read from file)"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator();

        expect: "that all filenames are accepted"
        validator.isValidImageExtension(pictureFile)

        where: "sample image names are "
        pictureFile << new File("src/test/resources/validImageNames.txt").readLines()

    }

    @Unroll("Checking image name #pictureFile")
    def " Valid images are PNG and JPEG file (customize generator)"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator();

        expect: "that all filenames are accepted"
        !validator.isValidImageExtension(pictureFile)

        where: "sample image names are "
        pictureFile << new InvalidNamesGen("src/test/resources/invalidImageNames.txt")

    }

    @Unroll("Checking image name #pictureFile with result=#result")
    def " Valid images are PNG and JPEG file ( multivalued iterator)"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator();

        expect: "that all filenames are categorized correctly"
        validator.isValidImageExtension(pictureFile) == result

        where: "sample image names are "
        [pictureFile, result] << new MultiVarReader("src/test/resources/imageNames.txt")

    }


}